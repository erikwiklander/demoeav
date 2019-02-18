package io.wiklandia.demoeav.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Ent extends BaseEntity {

    @Column(nullable = false)
    private String type;

    public static Ent create(String type) {
        return new Ent(type);
    }


}