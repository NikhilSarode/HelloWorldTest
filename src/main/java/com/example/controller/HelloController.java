package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from HelloWorldTest 5";
    }

    @GetMapping("/helloAuthUser")
    public String sayHelloUser(@RequestHeader("X-Username") String user,
                           @RequestHeader("X-Roles") String roles) {
        return "Hello from HelloWorldTest user:"+user+",roles:-"+roles;
    }

    @GetMapping("/helloAuthAdmin")
    public String sayHelloAdmin(@RequestHeader("X-Username") String user,
                           @RequestHeader("X-Roles") String roles) {
        return "Hello from HelloWorldTest admin:"+user+",roles:-"+roles;
    }
}