package io.wiklandia.demoeav.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
public class EntAttr {
    @ManyToOne
    private Ent ent;

    @ManyToOne
    private Attr attr;
}
