package com.example.iotchallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        final ArrayList<Response> responseMessages = new ArrayList<>();
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(ResponseEntity.class)
                .directModelSubstitute(ResponseEntity.class, String.class)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .apiInfo(apiInfo()); //TODO cleanup

    }

    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("IOT Challenge API")
                .description("API for User Profile handling")
                .version("1.0").build();
    }
}
