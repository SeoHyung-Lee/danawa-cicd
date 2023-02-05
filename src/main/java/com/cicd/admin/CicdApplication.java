package com.cicd.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CicdApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CicdApplication.class, args);
    }
}
