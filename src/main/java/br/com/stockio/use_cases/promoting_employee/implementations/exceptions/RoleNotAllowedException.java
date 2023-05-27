package br.com.stockio.use_cases.promoting_employee.implementations.exceptions;

import br.com.stockio.entities.Role;
import br.com.stockio.mapped_exceptions.specifics.InputMappedException;

public class RoleNotAllowedException extends InputMappedException {
    public RoleNotAllowedException(Role role) {
        super("Getting promoted to the role '" + role.getName() + "' is not an allowed option to the employee right now");
    }
}
