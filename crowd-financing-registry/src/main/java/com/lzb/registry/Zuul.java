package com.lzb.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Zuul {
    public static void main(String[] args) {
        SpringApplication.run(Zuul.class);
    }
}
