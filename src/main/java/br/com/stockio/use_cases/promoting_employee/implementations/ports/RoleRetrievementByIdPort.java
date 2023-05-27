package br.com.stockio.use_cases.promoting_employee.implementations.ports;

import br.com.stockio.entities.Role;
import br.com.stockio.ports.specifics.functions.FunctionPort;

import java.util.Optional;
import java.util.UUID;

public abstract class RoleRetrievementByIdPort extends FunctionPort<UUID, Optional<Role>> {
}
