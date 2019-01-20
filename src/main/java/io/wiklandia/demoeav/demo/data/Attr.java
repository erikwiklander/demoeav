package io.wiklandia.demoeav.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Attr extends BaseEntity {

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private AttrType type;

    private Attr(String name) {
        this.name = name;
    }

    public static Attr create(String name) {
        return new Attr(name);
    }
}
