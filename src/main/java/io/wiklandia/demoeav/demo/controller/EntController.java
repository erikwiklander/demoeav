package io.wiklandia.demoeav.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class EntController {

    @GetMapping("yes/ents")
    public List<EntDto> getEnts() {
        return Collections.emptyList();
    }

    @PostMapping("yes/no")
    public void posting(@RequestBody Map<String, Object> body) {

        for (Object vals : body.values()) {
            log.info("{}", vals.getClass().getSimpleName());
        }

        log.info("{}", body);
    }


}
