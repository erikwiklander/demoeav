package io.wiklandia.demoeav.demo.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class EntDto {

    private final UUID id;

    private Map<String, Object> attrs = new HashMap<>();

    public void add(String attrName, Object value) {
        attrs.put(attrName, value);
    }

    void addAll(Map<String, Object> vals) {
        attrs.putAll(vals);
    }


}
