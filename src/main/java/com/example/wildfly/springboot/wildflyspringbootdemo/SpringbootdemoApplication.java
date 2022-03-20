package com.example.wildfly.springboot.wildflyspringbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64;
import com.google.gson.*;

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
    Map<String, Object> hello(@PathVariable String name, @RequestHeader("authorization") String bearer) {
        HashMap<String, Object> response = new HashMap<>();
     
        String[] parts = bearer.substring(7).split("\\.");
        String b64payload = parts[1];
        String jsonString = new String(Base64.getDecoder().decode(b64payload));
     
        final Map jwtMap = new Gson().fromJson(jsonString, Map.class);
        String subName = (String)jwtMap.get("name");
        String n = (subName != null)? subName : name;
     
        String echo = "Hi " + n + " !";
        response.put("message", echo);
        return response;
 
    }
}
