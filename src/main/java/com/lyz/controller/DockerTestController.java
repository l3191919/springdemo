package com.lyz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/docker")
public class DockerTestController {

    @GetMapping(value = "/hello")
    public String dockerTestGetHello(){
        return "dockerTestGetHello";
    }

}
