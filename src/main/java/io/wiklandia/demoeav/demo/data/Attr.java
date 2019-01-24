package io.wiklandia.demoeav.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "name", name = "attr_name_key")
)
public class Attr extends BaseEntity {

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
