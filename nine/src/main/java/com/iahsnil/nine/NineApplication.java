package com.iahsnil.nine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NineApplication {

    public static void main(String[] args) {
        SpringApplication.run(NineApplication.class, args);
    }

}
