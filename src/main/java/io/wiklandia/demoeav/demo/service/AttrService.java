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
                .orElseGet(() -> AttrType.guessType(value));
        return convert(attrType, value);
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
