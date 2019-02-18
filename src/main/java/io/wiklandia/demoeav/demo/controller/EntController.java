package io.wiklandia.demoeav.demo.controller;

import io.wiklandia.demoeav.demo.service.EntService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
public class EntController {

    private final EntService entService;

    @GetMapping("objects")
    public List<EntDto> getAll() {
        return entService.assembleAll();
    }

    @GetMapping("type/{type}")
    public List<EntDto> getByType(@PathVariable String type) {
        return entService.assembleByType(type);
    }

    @PostMapping("objects")
    public EntDto posting(@RequestParam String type, @RequestBody Map<String, Object> body) {
        return entService.assemble(entService.createEnt(type, body));
    }

    @PatchMapping("objects/{id}")
    public EntDto patching(@PathVariable UUID id, @RequestBody Map<String, Object> body) {
        return entService.assemble(entService.patchEnt(id, body));
    }

    @GetMapping("objects/{id}")
    public EntDto getting(@PathVariable UUID id) {
        return entService.assemble(id);
    }

    @PutMapping("objects/{id}")
    public EntDto putting(@PathVariable UUID id, @RequestBody Map<String, Object> body) {
        return entService.assemble(entService.putEnt(id, body));
    }

}
