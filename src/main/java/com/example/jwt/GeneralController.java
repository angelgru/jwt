package com.example.jwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {

    @GetMapping("/all")
    public String getAll() {
        return "Some dummy test text";
    }
}
