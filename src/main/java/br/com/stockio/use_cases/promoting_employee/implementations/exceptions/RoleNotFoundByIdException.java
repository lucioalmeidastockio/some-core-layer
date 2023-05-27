package br.com.stockio.use_cases.promoting_employee.implementations.exceptions;

import br.com.stockio.mapped_exceptions.specifics.NotFoundMappedException;

import java.util.UUID;

public class RoleNotFoundByIdException extends NotFoundMappedException {
    public RoleNotFoundByIdException(UUID roleId) {
        super("Role of ID '" + roleId.toString() + "' was not found");
    }
}
