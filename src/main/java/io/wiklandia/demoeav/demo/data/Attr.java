package io.wiklandia.demoeav.demo.data;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@EqualsAndHashCode(of = "name", callSuper = false)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "name", name = "attr_name_key")
)
public class Attr extends BaseEntity {

    @Column(updatable = false)
    private String name;

    @Column(updatable = false)
    @Enumerated(EnumType.STRING)
    private AttrType type;

}
