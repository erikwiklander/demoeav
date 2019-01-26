package io.wiklandia.demoeav.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Ent extends BaseEntity {

    private String type;

    public static Ent create() {
        return new Ent();
    }

}