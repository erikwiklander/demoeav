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
@Table(
        uniqueConstraints =
                {
                        @UniqueConstraint(columnNames = {"ent_id", "attr_id"}, name = "eav_ent_id_attr_id_key")
                },
        indexes =
                {
                        @Index(columnList = "ent_id", name = "eav_ent_id_index"),
                        @Index(columnList = "attr_id", name = "eav_attr_id_index"),
                        @Index(columnList = "stringValue", name = "eav_string_value_index"),
                        @Index(columnList = "numberValue", name = "eav_number_value_index"),
                        @Index(columnList = "dateValue", name = "eav_date_value_index"),
                        @Index(columnList = "booleanValue", name = "eav_boolean_value_index")
                }
)
public class Eav extends BaseEntity {

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "eav_ent_id_fkey"))
    private Ent ent;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "eav_attr_id_fkey"))
    private Attr attr;

    @Column(columnDefinition = "text")
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
