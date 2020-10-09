package com.lzb.service;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@MapperScan("com.lzb.service.mapper")
@EnableDiscoveryClient
public class AdminServiceSpringBoot {
    public static void main(String[] args) {
        SpringApplication.run(AdminServiceSpringBoot.class);
    }
}
