package com.jbohorquez.microservices_users.infrastructure.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

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