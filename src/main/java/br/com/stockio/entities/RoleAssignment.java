package br.com.stockio.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class RoleAssignment implements Entity{

    protected LocalDateTime startingMoment;
    protected LocalDateTime endingMoment;
    protected Role role;

    public abstract void endAssignment();
}


