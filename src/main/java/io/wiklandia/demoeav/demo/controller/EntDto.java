package io.wiklandia.demoeav.demo.controller;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
public class EntDto implements Serializable {

    private final Long id;

    private final String type;

    private Map<String, Object> attrs = new HashMap<>();

    public void add(String attrName, Object value) {
        attrs.put(attrName, value);
    }

    void addAll(Map<String, Object> vals) {
        attrs.putAll(vals);
    }


}
