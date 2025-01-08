package net.javaguides.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {
    
    @Value("${spring.datasource.url}")
    private String dbUrl;
    
    @Value("${spring.datasource.username}")
    private String dbUsername;
    
    @Value("${spring.datasource.password}")
    private String dbPassword;

    @GetMapping("/demo")
    public String demo() {
        return String.format("DB URL: %s, Username: %s, Password: %s", dbUrl, dbUsername, dbPassword);
    }
}





















    
      

