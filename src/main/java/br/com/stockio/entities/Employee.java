package br.com.stockio.entities;

import br.com.stockio.entities.factories.RoleAssignmentFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public abstract class Employee implements Entity {

    protected UUID id;
    protected String name;
    protected List<RoleAssignment> roleAssignments = new ArrayList<>();

    protected final RoleAssignmentFactory roleAssignmentFactory;

    protected Employee(RoleAssignmentFactory roleAssignmentFactory){
        this.roleAssignmentFactory = roleAssignmentFactory;
    }

    public abstract void assumeNewRole(Role newRole);
    public abstract Optional<RoleAssignment> getCurrentRoleAssignment();
    public abstract void endCurrentAssignment();

}





