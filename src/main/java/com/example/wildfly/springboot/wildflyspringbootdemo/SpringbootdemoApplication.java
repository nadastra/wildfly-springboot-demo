package com.example.wildfly.springboot.wildflyspringbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

// Spring Boot 2.x
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
 
@SpringBootApplication
public class SpringbootdemoApplication extends SpringBootServletInitializer {
 
    public static void main(String[] args) {
        SpringApplication.run(applicationClass, args);
    }
 
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
 
    private static Class<SpringbootdemoApplication> applicationClass = SpringbootdemoApplication.class;
}
 
@RestController
class HelloController {
 
    @RequestMapping("/hello/{name}")
    Map<String, Object> hello(@PathVariable String name) {
        HashMap<String, Object> response = new HashMap<>();
        String echo = "Hi " + name + " !";
        response.put("message", echo);
        return response;
 
    }
}
