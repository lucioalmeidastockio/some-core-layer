package br.com.stockio.use_cases.promoting_employee.factories.dependency_wrapper;

import br.com.stockio.app_utils.use_case_dependency_wrapper.UseCaseDependencyWrapper;
import br.com.stockio.loggers.Logger;
import br.com.stockio.use_cases.promoting_employee.implementations.ports.CareerPathRetrievementPort;
import br.com.stockio.use_cases.promoting_employee.implementations.ports.EmployeeAssignmentUpdatePort;
import br.com.stockio.use_cases.promoting_employee.implementations.ports.EmployeeRetrievementByIdPort;
import br.com.stockio.use_cases.promoting_employee.implementations.ports.RoleRetrievementByIdPort;
import lombok.Builder;

@Builder
public class PromotingEmployeeUseCaseDependencyWrapper extends UseCaseDependencyWrapper {

    private final Logger logger;
    private final EmployeeRetrievementByIdPort employeeRetrievementByIdPortAdapter;
    private final RoleRetrievementByIdPort roleRetrievementByIdPortAdapter;
    private final CareerPathRetrievementPort careerPathRetrievementPortAdapter;
    private final EmployeeAssignmentUpdatePort employeeUpdatePortAdapter;

    public Logger getLogger() {
        return this.getValueWithNullSafety(this.logger);
    }

    public EmployeeRetrievementByIdPort getEmployeeRetrievementByIdPortAdapter() {
        return this.getValueWithNullSafety(this.employeeRetrievementByIdPortAdapter);
    }

    public RoleRetrievementByIdPort getRoleRetrievementByIdPortAdapter() {
        return this.getValueWithNullSafety(this.roleRetrievementByIdPortAdapter);
    }

    public CareerPathRetrievementPort getCareerPathRetrievementPortAdapter() {
        return this.getValueWithNullSafety(this.careerPathRetrievementPortAdapter);
    }

    public EmployeeAssignmentUpdatePort getEmployeeUpdatePortAdapter() {
        return this.getValueWithNullSafety(this.employeeUpdatePortAdapter);
    }

}
