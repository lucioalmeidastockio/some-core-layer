package br.com.stockio.use_cases.promoting_employee.implementations.ports;

import br.com.stockio.entities.Role;
import br.com.stockio.ports.specifics.suppliers.SupplierPort;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class CareerPathRetrievementPort extends SupplierPort<Map<UUID, List<Role>>> {
}
