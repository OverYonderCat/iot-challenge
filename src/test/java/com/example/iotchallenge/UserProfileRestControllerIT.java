package com.example.iotchallenge;

import com.example.iotchallenge.model.UserProfile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserProfileRestControllerIT {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void profileShouldContainExpectedData() throws Exception {
        var url = "http://localhost:" + port + "/user-profiles/1";
        var expectedName = "Lucky Luke";
        var expectedUrl = "http://www.lucky-luke.com/en/";
        var userProfile = this.restTemplate.getForObject(url, UserProfile.class);

        assertAll(
                () -> assertEquals(1L, userProfile.getId()),
                () -> assertEquals(expectedName, userProfile.getName()),
                () -> assertEquals(expectedUrl, userProfile.getLink())
        );
    }
}
