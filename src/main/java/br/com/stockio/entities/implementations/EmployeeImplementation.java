package br.com.stockio.entities.implementations;

import br.com.stockio.entities.Employee;
import br.com.stockio.entities.Role;
import br.com.stockio.entities.RoleAssignment;
import br.com.stockio.entities.factories.RoleAssignmentFactory;

import java.time.LocalDateTime;
import java.util.Optional;

public class EmployeeImplementation extends Employee {

    public EmployeeImplementation(RoleAssignmentFactory roleAssignmentFactory) {
        super(roleAssignmentFactory);
    }

    @Override
    public void assumeNewRole(Role newRole) {
        this.endCurrentAssignment();
        var newRoleAssignment = this.roleAssignmentFactory.makeNewInstance();
        newRoleAssignment.setRole(newRole);
        newRoleAssignment.setStartingMoment(LocalDateTime.now());
        this.roleAssignments.add(newRoleAssignment);
    }

    @Override
    public Optional<RoleAssignment> getCurrentRoleAssignment() {
        return this.roleAssignments.stream()
                .filter(roleAssignment -> Optional.ofNullable(roleAssignment.getEndingMoment()).isEmpty())
                .findFirst();
    }

    @Override
    public void endCurrentAssignment() {
        this.getCurrentRoleAssignment().ifPresent(RoleAssignment::endAssignment);
    }
}
