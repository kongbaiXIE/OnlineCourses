package com.xzq.systemApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.xzq.*")
public class SystemApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApiApplication.class, args);
    }
}