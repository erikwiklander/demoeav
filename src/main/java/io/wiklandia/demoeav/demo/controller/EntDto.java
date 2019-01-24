package io.wiklandia.demoeav.demo.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class EntDto {

    private final Long id;

    private Map<String, Object> attrs = new HashMap<>();

    public void add(String attrName, Object value) {
        attrs.put(attrName, value);
    }

    void addAll(Map<String, Object> vals) {
        attrs.putAll(vals);
    }


}
