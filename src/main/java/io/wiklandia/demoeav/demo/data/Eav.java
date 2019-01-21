package io.wiklandia.demoeav.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"ent", "attr"}, callSuper = false)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"ent_id", "attr_id"})})
public class Eav extends BaseEntity {

    @ManyToOne
    private Ent ent;

    @ManyToOne
    private Attr attr;

    private String stringValue;
    @Column(precision = 25, scale = 5)
    private BigDecimal numberValue;
    private LocalDate dateValue;
    private Boolean booleanValue;

    private Eav(Object value, Ent ent, Attr attr) {
        attr.setType(getAttrType(value));
        this.attr = attr;
        this.ent = ent;
        setValue(value);
    }

    public static Eav create(Object value, Ent ent, Attr attr) {
        return new Eav(value, ent, attr);
    }

    public void setValue(Object value) {
        AttrType attrType = getAttrType(value);
        Assert.isTrue(attrType == this.attr.getType(), "Attribute Type mismatch!");
        attrType.getValueSetter().accept(this, value);
    }

    private AttrType getAttrType(Object value) {
        Assert.notNull(value, "Value cannot be null");
        AttrType attrType = AttrType.getType(value.getClass());
        Assert.notNull(attrType, "No support for this type: " + value.getClass().getSimpleName());
        return attrType;
    }


}
