package com.jbohorquez.emazon_hexagonal.infrastructure.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenApi(@Value("${appdescription}") String appDescription,
                                 @Value("${appversion}") String appVersion){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(TITLE)
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService(TERMS_OF_SERVICE_URL)
                        .license(new License().name(LICENSE_NAME).url(LICENSE_URL)));
    }
}