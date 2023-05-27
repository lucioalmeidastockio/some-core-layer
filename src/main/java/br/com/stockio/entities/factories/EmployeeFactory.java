package br.com.stockio.entities.factories;

import br.com.stockio.entities.Employee;
import br.com.stockio.entities.EntityFactory;
import br.com.stockio.entities.implementations.EmployeeImplementation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeFactory implements EntityFactory<Employee> {

    private final RoleAssignmentFactory roleAssignmentFactory;

    @Override
    public Employee makeNewInstance() {
        return new EmployeeImplementation(this.roleAssignmentFactory);
    }
}
