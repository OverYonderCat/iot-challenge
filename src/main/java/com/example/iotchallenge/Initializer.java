package com.example.iotchallenge;

import com.example.iotchallenge.model.UserProfile;
import com.example.iotchallenge.repository.UserProfileRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class Initializer implements CommandLineRunner {

    private final UserProfileRepository userProfileRepository;

    public Initializer(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        URL avatarFile = this.getClass().getResource("/lucky.jpg");
        byte[] avatar = readBytes(avatarFile);

        var joe = UserProfile.builder()
                .name("Lucky Luke")
                .description("I’m a poor, lonesome cowboy.")
                .link("http://www.lucky-luke.com/en/")
                .avatarImage(avatar)
                .build();
        userProfileRepository.save(joe);
    }

    private static byte[] readBytes(URL resource) throws IOException {
        try (var input = resource.openStream()) {
            return input.readAllBytes();
        }
    }
}
