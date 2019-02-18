package io.wiklandia.demoeav.demo.data;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Getter
@AllArgsConstructor
public enum AttrType {
    STRING(
            String.class,
            (eav, o) -> {
                eav.setStringValue((String) o);
            },
            Eav::getStringValue
    ),
    NUMBER(BigDecimal.class,
            (eav, o) -> {
                eav.setNumberValue((BigDecimal) o);
            },
            Eav::getNumberValue
    ),
    DATE(
            LocalDate.class,
            (eav, o) -> {
                eav.setDateValue((LocalDate) o);
            },
            Eav::getDateValue
    ),
    BOOLEAN(
            Boolean.class,
            (eav, o) -> {
                eav.setBooleanValue((Boolean) o);
            },
            Eav::getBooleanValue
    ),
    REL(
            Ent.class,
            (eav, o) -> {
                eav.setRelValue((Ent) o);
            },
            eav -> eav.getRelValue() == null ? null : eav.getRelValue().getId()
    );


    private Class clazz;
    private BiConsumer<Eav, Object> valueSetter;
    private Function<Eav, Object> valueGetter;
    private static Map<Class, AttrType> TYPE_BY_CLASS =
            Arrays.stream(values()).collect(
                    Collectors.toMap(attrType -> attrType.clazz, attrType -> attrType));


    public static AttrType getType(Class clazz) {
        log.info("getType {}", clazz);
        return TYPE_BY_CLASS.get(clazz);
    }

    public static AttrType guessType(Object value) {
        if (value instanceof Number) {
            return NUMBER;
        } else if (value instanceof Boolean) {
            return BOOLEAN;
        } else if (parsableDate(value)) {
            return DATE;
        } else if (value instanceof Ent) {
            return REL;
        } else {
            return STRING;
        }
    }

    private static boolean parsableDate(Object value) {
        try {
            LocalDate.parse(value.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
