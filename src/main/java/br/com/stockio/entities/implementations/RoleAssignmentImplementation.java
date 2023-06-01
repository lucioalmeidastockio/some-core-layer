package br.com.stockio.entities.implementations;

import br.com.stockio.entities.RoleAssignment;

import java.time.LocalDateTime;
import java.util.Optional;

public class RoleAssignmentImplementation extends RoleAssignment {
    @Override
    public void endAssignment() {
        if (Optional.ofNullable(this.endingMoment).isEmpty())
            this.endingMoment = LocalDateTime.now();
    }
}

