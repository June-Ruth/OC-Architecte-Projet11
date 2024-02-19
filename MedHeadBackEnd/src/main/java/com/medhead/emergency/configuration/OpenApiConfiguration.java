package com.medhead.emergency.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setEmail("example@email.com");
        contact.setName("Noémie BARRAL");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Medhead Emergency POC")
                .version("1.0")
                .contact(contact)
                .description("This API expose endpoint of Medhead's system of emergency for POC")
                .license(mitLicense);

        return new OpenAPI().info(info);
    }
}
