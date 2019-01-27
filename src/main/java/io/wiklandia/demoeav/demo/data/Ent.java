package io.wiklandia.demoeav.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "type", name = "ent_type_key")
)
public class Ent extends BaseEntity {

    @Column(nullable = false)
    private String type;

    public static Ent create(String type) {
        return new Ent(type);
    }


}