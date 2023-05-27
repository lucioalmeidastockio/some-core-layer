package br.com.stockio.use_cases.promoting_employee.implementations;

import br.com.stockio.entities.Employee;
import br.com.stockio.entities.Role;
import br.com.stockio.loggers.Logger;
import br.com.stockio.use_cases.correlations.UseCaseExecutionCorrelation;
import br.com.stockio.use_cases.promoting_employee.PromotingEmployeeUseCase;
import br.com.stockio.use_cases.promoting_employee.implementations.exceptions.EmployeeNotFoundByIdException;
import br.com.stockio.use_cases.promoting_employee.implementations.exceptions.RoleNotAllowedException;
import br.com.stockio.use_cases.promoting_employee.implementations.exceptions.RoleNotFoundByIdException;
import br.com.stockio.use_cases.promoting_employee.implementations.ports.CareerPathRetrievementPort;
import br.com.stockio.use_cases.promoting_employee.implementations.ports.EmployeeAssignmentUpdatePort;
import br.com.stockio.use_cases.promoting_employee.implementations.ports.EmployeeRetrievementByIdPort;
import br.com.stockio.use_cases.promoting_employee.implementations.ports.RoleRetrievementByIdPort;
import br.com.stockio.use_cases.promoting_employee.io.inputs.PromotingEmployeeUseCaseInput;

import java.util.List;
import java.util.Optional;

public class PromotingEmployeeUseCaseImplementation extends PromotingEmployeeUseCase {

    private final EmployeeRetrievementByIdPort employeeRetrievementByIdPort;
    private final RoleRetrievementByIdPort roleRetrievementByIdPort;
    private final CareerPathRetrievementPort careerPathRetrievementPort;
    private final EmployeeAssignmentUpdatePort employeeUpdatePort;

    public PromotingEmployeeUseCaseImplementation(
            Logger logger,
            EmployeeRetrievementByIdPort employeeRetrievementByIdPort,
            RoleRetrievementByIdPort roleRetrievementByIdPort,
            CareerPathRetrievementPort careerPathRetrievementPort,
            EmployeeAssignmentUpdatePort employeeUpdatePort) {
        super(logger);
        this.employeeRetrievementByIdPort = employeeRetrievementByIdPort;
        this.roleRetrievementByIdPort = roleRetrievementByIdPort;
        this.careerPathRetrievementPort = careerPathRetrievementPort;
        this.employeeUpdatePort = employeeUpdatePort;
    }

    @Override
    protected void applyInternalLogic(PromotingEmployeeUseCaseInput input, UseCaseExecutionCorrelation correlation) {
        var employeeBeingPromoted = this.getEmployeeFrom(input, correlation);
        var roleToMakeNewAssignment = this.getRoleFrom(input, correlation);
        employeeBeingPromoted.getCurrentRoleAssignment().ifPresent(currentRoleAssignment -> {
            var currentAllowedCareerPath = this.careerPathRetrievementPort.executePort(correlation);
            var allowedPathToTheCurrentRoleAssigned = Optional.ofNullable(currentAllowedCareerPath.get(currentRoleAssignment.getRole().getId()));
            allowedPathToTheCurrentRoleAssigned.ifPresent(allowedPath -> this.handleValidationFor(allowedPath, roleToMakeNewAssignment));
        });
        employeeBeingPromoted.assumeNewRole(roleToMakeNewAssignment);
        this.employeeUpdatePort.executePortOn(employeeBeingPromoted, correlation);
    }

    private Employee getEmployeeFrom(PromotingEmployeeUseCaseInput input, UseCaseExecutionCorrelation correlation) {
        return this.employeeRetrievementByIdPort.executePortOn(input.getEmployeeId(), correlation)
                .orElseThrow(() -> new EmployeeNotFoundByIdException(input.getEmployeeId()));
    }

    private Role getRoleFrom(PromotingEmployeeUseCaseInput input, UseCaseExecutionCorrelation correlation) {
        return  this.roleRetrievementByIdPort.executePortOn(input.getRoleId(), correlation)
                .orElseThrow(() -> new RoleNotFoundByIdException(input.getRoleId()));
    }

    private void handleValidationFor(List<Role> allowedPath, Role role) {
        var roleSupposedToBeAssignedIsPresentAmongTheAllowedOnes = allowedPath.stream()
                .anyMatch(allowedStepInPath -> role.getId().equals(allowedStepInPath.getId()));
        if (!roleSupposedToBeAssignedIsPresentAmongTheAllowedOnes)
            throw new RoleNotAllowedException(role);
    }
}
