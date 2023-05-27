package br.com.stockio.use_cases.promoting_employee.implementations.exceptions;

import br.com.stockio.mapped_exceptions.specifics.NotFoundMappedException;

import java.util.UUID;

public class EmployeeNotFoundByIdException extends NotFoundMappedException {
    public EmployeeNotFoundByIdException(UUID employeeId) {
        super("Employee of ID '" + employeeId.toString() + "' was not found");
    }
}
