package io.wiklandia.demoeav.demo.service;

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

    private Object convert(AttrType attrType, Object value) {
        switch (attrType) {
            case STRING:
                return value.toString();
            case NUMBER:
                return new BigDecimal(value.toString());
            case DATE:
                return LocalDate.parse(value.toString());
        }
        return value;
    }


}
