package io.wiklandia.demoeav.demo.data;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum AttrType {
    STRING(
            String.class,
            (eav, o) -> {
                eav.setStringValue((String) o);
            }),
    NUMBER(BigDecimal.class,
            (eav, o) -> {
                eav.setNumberValue((BigDecimal) o);
            }
    ),
    DATE(
            LocalDate.class,
            (eav, o) -> {
                eav.setDateValue((LocalDate) o);
            }
    );

    private Class clazz;
    private BiConsumer<Eav, Object> valueSetter;
    private static Map<Class, AttrType> TYPE_BY_CLASS =
            Arrays.stream(values()).collect(
                    Collectors.toMap(attrType -> attrType.clazz, attrType -> attrType));


    public static AttrType getType(Class clazz) {
        return TYPE_BY_CLASS.get(clazz);
    }

    public static boolean isValidType(Class clazz) {
        return getType(clazz) != null;
    }


}
