package com.fastcampus.javaallinone.project.mycontact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldcontroller {

    @GetMapping(value = "/api/helloWorld")
    public String helloWorld() {
        return "Hello World";
    }
}
