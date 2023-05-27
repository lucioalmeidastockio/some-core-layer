package br.com.stockio.entities.factories;

import br.com.stockio.entities.EntityFactory;
import br.com.stockio.entities.RoleAssignment;
import br.com.stockio.entities.implementations.RoleAssignmentImplementation;

public class RoleAssignmentFactory implements EntityFactory<RoleAssignment> {
    @Override
    public RoleAssignment makeNewInstance() {
        return new RoleAssignmentImplementation();
    }
}
