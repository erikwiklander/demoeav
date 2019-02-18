package io.wiklandia.demoeav.demo.service;

import io.wiklandia.demoeav.demo.controller.EntDto;
import io.wiklandia.demoeav.demo.data.Ent;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface EntService {
    Ent createEnt(String type);

    Ent createEnt(String type, String attr, Object value);

    Ent createEnt(String type, Map<String, Object> attrs);

    Ent patchEnt(UUID entId, Map<String, Object> attrs);

    Ent putEnt(UUID entId, Map<String, Object> attrs);

    void saveValue(Ent ent, String attrName, Object value);

    EntDto assemble(Ent ent);

    EntDto assemble(UUID id);

    List<EntDto> assembleByType(String type);

    List<EntDto> assembleAll();
}
