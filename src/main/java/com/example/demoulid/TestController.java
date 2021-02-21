package com.example.demoulid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    EJGService service;

    @GetMapping("/test")
    public void test() {
        service.something();
    }
}
