package io.wiklandia.demoeav.demo.service;

import io.wiklandia.demoeav.demo.data.Attr;
import io.wiklandia.demoeav.demo.data.AttrRepository;
import io.wiklandia.demoeav.demo.data.AttrType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class AttrService {

    private final AttrRepository attrRepository;

    Object convert(String attrName, Object value) {
        if (value == null) {
            return null;
        }
        AttrType attrType = attrRepository
                .findByName(attrName)
                .map(Attr::getType)
                .orElseGet(() -> guessAttrType(value));
        return convert(attrType, value);
    }

    private AttrType guessAttrType(Object value) {
        if (value instanceof Number) {
            return AttrType.NUMBER;
        } else if (value instanceof Boolean) {
            return AttrType.BOOLEAN;
        } else if (parsableDate(value)) {
            return AttrType.DATE;
        } else {
            return AttrType.STRING;
        }
    }

    private boolean parsableDate(Object value) {
        try {
            LocalDate.parse(value.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Object convert(AttrType attrType, Object value) {
        switch (attrType) {
            case NUMBER:
                return new BigDecimal(value.toString());
            case DATE:
                return LocalDate.parse(value.toString());
            case BOOLEAN:
                return Boolean.valueOf(value.toString());
            default:
                return value.toString();
        }
    }


}
