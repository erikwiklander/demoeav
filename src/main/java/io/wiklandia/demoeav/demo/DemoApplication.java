package io.wiklandia.demoeav.demo;

import io.wiklandia.demoeav.demo.data.AttrRepository;
import io.wiklandia.demoeav.demo.data.EavRepository;
import io.wiklandia.demoeav.demo.data.Ent;
import io.wiklandia.demoeav.demo.data.EntRepository;
import io.wiklandia.demoeav.demo.service.EntService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AttrRepository attrRepository, EntRepository entRepository, EavRepository eavRepository, EntService entService) {
        return args -> {

            Ent e = entService.createEnt();
            entService.saveValue(e, "Name", "Erik");
            entService.saveValue(e, "Efternamn", "Wiklander");
            entService.saveValue(e, "Efternamn", "Johansson");
            entService.saveValue(e, "Efternamn", "Red");
//            entService.saveValue(e, "Ålder", 10L);
//            entService.saveValue(e, "11", 10);
            entService.saveValue(e, "birth", LocalDate.now());
            entService.saveValue(e, "awesome", new BigDecimal("567.10"));
            log.info("___");
            entService.saveValue(e, "awesome", null);
            log.info("___");
            entService.saveValue(e, "tomt", null);

            entService.saveValue(e, "bd", new BigDecimal("10.19"));
        };
    }

}

