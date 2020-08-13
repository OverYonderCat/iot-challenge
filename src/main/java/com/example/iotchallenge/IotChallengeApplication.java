package com.example.iotchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class IotChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotChallengeApplication.class, args);
    }
}
