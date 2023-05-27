package br.com.stockio.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class Role implements Entity{

    protected UUID id;
    protected String name;

}
