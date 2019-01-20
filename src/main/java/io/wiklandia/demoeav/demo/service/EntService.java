package io.wiklandia.demoeav.demo.service;

import io.wiklandia.demoeav.demo.data.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class EntService {

    private final EntRepository entRepository;
    private final AttrRepository attrRepository;
    private final EavRepository eavRepository;

    public Ent createEnt() {
        return entRepository.save(Ent.create());
    }

    private Attr findOrCreate(String attrName) {
        Attr attr = attrRepository.findByName(attrName).orElseGet(() -> Attr.create(attrName));
        if (attr.getId() == null) {
            log.info("Creating new attribute: {}", attrName);
        }
        return attrRepository.save(attr);
    }

    @Transactional
    public void saveValue(Ent ent, String attrName, Object value) {
        log.info("Saving attribute value: {} {} {}", ent, attrName, value);
        if (value == null) {
            deleteValue(ent, attrName);
        } else {
            Attr attr = findOrCreate(attrName);
            EntAttr entAttr = EntAttr.of(ent, attr);
            Eav eav = eavRepository.findByEntAttr(entAttr).orElseGet(() -> Eav.create(value, entAttr));
            eav.setValue(value);
            eavRepository.save(eav);
        }
    }

    private void deleteValue(Ent ent, String attrName) {
        attrRepository.findByName(attrName).ifPresent(
                attr -> eavRepository.deleteByEntAttr(EntAttr.of(ent, attr)));
    }

}
