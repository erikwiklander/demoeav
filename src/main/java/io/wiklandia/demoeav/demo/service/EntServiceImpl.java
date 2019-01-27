package io.wiklandia.demoeav.demo.service;

import io.wiklandia.demoeav.demo.controller.EntDto;
import io.wiklandia.demoeav.demo.data.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@AllArgsConstructor
public class EntServiceImpl implements EntService {

    private final EntRepository entRepository;
    private final AttrRepository attrRepository;
    private final EavRepository eavRepository;
    private final AttrService attrService;

    @Override
    @Transactional
    public Ent createEnt(String type) {
        return entRepository.save(Ent.create(type));
    }

    @Override
    @Transactional
    public Ent createEnt(String type, Map<String, Object> attrs) {
        Ent ent = createEnt(type);
        return saveAttributes(ent, attrs);
    }

    @Override
    @Transactional
    public Ent patchEnt(UUID entId, Map<String, Object> attrs) {
        Ent ent = entRepository.findById(entId).orElseThrow(() -> new IllegalStateException("Not found!"));
        return saveAttributes(ent, attrs);
    }

    @Override
    @Transactional
    public Ent putEnt(UUID entId, Map<String, Object> attrs) {
        Ent ent = entRepository.findById(entId).orElseThrow(() -> new IllegalStateException("Not found!"));
        eavRepository.findByEnt(ent)
                .stream()
                .map(Eav::getAttr)
                .map(Attr::getName)
                .forEach(s -> attrs.putIfAbsent(s, null));
        return saveAttributes(ent, attrs);
    }

    private Ent saveAttributes(Ent ent, Map<String, Object> attrs) {
        for (Map.Entry<String, Object> attr : attrs.entrySet()) {
            String attrName = attr.getKey();
            saveValue(ent, attrName, attrService.convert(attrName, attr.getValue()));
        }
        return ent;
    }

    private Attr findOrCreate(String attrName) {
        Attr attr = attrRepository.findByName(attrName).orElseGet(() -> Attr.create(attrName));
        if (attr.getId() == null) {
            log.info("Creating new attribute: {}", attrName);
        }
        return attrRepository.save(attr);
    }

    @Override
    @Transactional
    public void saveValue(Ent ent, String attrName, Object value) {
        log.info("Saving attribute value: {} {} {}", ent, attrName, value);
        if (value == null) {
            deleteValue(ent, attrName);
        } else {
            Attr attr = findOrCreate(attrName);
            Eav eav = eavRepository.findByEntAndAttr(ent, attr).orElseGet(() -> Eav.create(value, ent, attr));
            eav.setValue(value);
            eavRepository.save(eav);
        }
    }

    private void deleteValue(Ent ent, String attrName) {
        attrRepository.findByName(attrName).ifPresent(
                attr -> eavRepository.deleteByEntAndAttr(ent, attr));
    }

    @Override
    public EntDto assemble(Ent ent) {
        EntDto entDto = new EntDto(ent.getId(), ent.getType());
        eavRepository.findByEnt(ent)
                .forEach(eav -> entDto.add(
                        eav.getAttr().getName(),
                        eav.getAttr().getType().getValueGetter().apply(eav)));
        return entDto;
    }

    @Override
    public EntDto assemble(UUID id) {
        return entRepository.findById(id)
                .map(this::assemble)
                .orElseThrow(() -> new IllegalArgumentException("No such id: " + id));
    }

    private List<EntDto> assemble(Iterable<Ent> ents) {
        return StreamSupport.stream(ents.spliterator(), false)
                .map(this::assemble)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntDto> assembleAll() {
        return assemble(entRepository.findAll());
    }
}
