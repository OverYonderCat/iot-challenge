package com.example.iotchallenge.controller;

import com.example.iotchallenge.model.UserProfile;
import com.example.iotchallenge.repository.UserProfileRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/user-profiles")
@CrossOrigin(maxAge = 3600)
@RestController
public class UserProfileController {

    private final UserProfileRepository userProfileRepository;

    public UserProfileController(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation("Get all UserProfiles")
    public List<UserProfile> getUserProfiles() {
        return userProfileRepository.findAll();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation("Get UserProfile by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "UserProfile with given Id not found")
    })
    public ResponseEntity<UserProfile> getUserProfileById(@ApiParam(required = true) @PathVariable Long id) {
        var userProfile = userProfileRepository.findById(id);
        return userProfile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation("Update UserProfile by Id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "UserProfile successfully updated"),
            @ApiResponse(code = 400, message = "Failed - Input Json not valid"),
            @ApiResponse(code = 404, message = "UserProfile with given Id not found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity updateUserProfile(@ApiParam(required = true) @PathVariable Long id,
                                            @Valid @RequestBody UserProfile userProfileUpdate) {
        var userProfile = userProfileRepository.findById(id);
        if (userProfile.isPresent()) {
            userProfileRepository.save(userProfileUpdate);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
