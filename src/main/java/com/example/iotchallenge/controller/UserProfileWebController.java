package com.example.iotchallenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserProfileWebController {
    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
}
