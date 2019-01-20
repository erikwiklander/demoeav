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
@EqualsAndHashCode(of = "entAttr", callSuper = false)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"ent_id", "attr_id"})})
public class Eav extends BaseEntity {

    @Embedded
    EntAttr entAttr;

    @Enumerated(EnumType.STRING)
    private AttrType type;

    private String stringValue;
    private BigDecimal numberValue;
    private LocalDate dateValue;


    private Eav(Object value, EntAttr entAttr) {
        this.type = getAttrType(value);
        this.entAttr = entAttr;
        setValue(value);
    }

    public static Eav create(Object value, EntAttr entAttr) {
        return new Eav(value, entAttr);
    }

    public void setValue(Object value) {
        if (type == null) {
            throw new IllegalStateException("Attribute type not set!");
        }
        AttrType attrType = getAttrType(value);
        Assert.isTrue(attrType == this.type, "Attribute Type mismatch!");
        attrType.getValueSetter().accept(this, value);
    }

    private AttrType getAttrType(Object value) {
        Assert.notNull(value, "Value cannot be null");
        AttrType attrType = AttrType.getType(value.getClass());
        Assert.notNull(attrType, "No support for this type: " + value.getClass().getSimpleName());
        return attrType;
    }


}
