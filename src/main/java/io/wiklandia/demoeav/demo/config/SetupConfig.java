package io.wiklandia.demoeav.demo.config;

import io.wiklandia.demoeav.demo.data.Ent;
import io.wiklandia.demoeav.demo.service.EntService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SetupConfig {


    /**
     * Creates meta data for the table
     */
    @Bean
    public CommandLineRunner setup(EntService entService) {
        return args -> {

            Ent tableConfig = entService.createEnt("admin_table");
            entService.saveValue(tableConfig, "name", "Tables");
            entService.saveValue(tableConfig, "type", "admin_table");
            entService.saveValue(tableConfig, "sub_type", "admin_column");

            Ent col1 = entService.createEnt("admin_column");
            entService.saveValue(col1, "attribute_name", "Name");
            entService.saveValue(col1, "attribute_type", "string");
            entService.saveValue(col1, "table", tableConfig);

            entService.createEnt("admin_attribute", "type", "string");
            entService.createEnt("admin_attribute", "type", "integer");
            entService.createEnt("admin_attribute", "type", "decimal");
            entService.createEnt("admin_attribute", "type", "date");
            entService.createEnt("admin_attribute", "type", "boolean");
        };
    }

}
