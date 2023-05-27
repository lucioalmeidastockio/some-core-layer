package br.com.stockio.entities.factories;

import br.com.stockio.entities.EntityFactory;
import br.com.stockio.entities.Role;
import br.com.stockio.entities.implementations.RoleImplementation;

public class RoleFactory implements EntityFactory<Role> {

    @Override
    public Role makeNewInstance() {
        return new RoleImplementation();
    }
}
