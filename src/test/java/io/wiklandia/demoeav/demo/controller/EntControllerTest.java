package io.wiklandia.demoeav.demo.controller;

import com.google.common.collect.ImmutableMap;
import io.wiklandia.demoeav.demo.service.EntService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EntController.class)
public class EntControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EntService service;

    @Test
    public void getAll() throws Exception {

        Map<String, Object> attrs = ImmutableMap.of("a1", "text", "a2", true, "a3", new BigDecimal("10.12"));

        EntDto e1 = createEntDto(attrs);
        EntDto e2 = createEntDto(attrs);

        when(service.assembleAll()).thenReturn(Arrays.asList(
                e1, e2
        ));


        mvc.perform(
                get("/objects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].*", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value(e1.getId().toString()))
                .andExpect(jsonPath("$[0].type").value(e1.getType()))
                .andExpect(jsonPath("$[0].attrs.*", hasSize(3)))
                .andExpect(jsonPath("$[0].attrs.a1").value("text"))
                .andExpect(jsonPath("$[0].attrs.a2").value(true))
                .andExpect(jsonPath("$[0].attrs.a3").value(10.12))

        ;
    }

    private EntDto createEntDto(Map<String, Object> attrs) {
        EntDto entDto = new EntDto(1L, "test");
        entDto.addAll(attrs);
        return entDto;
    }

}