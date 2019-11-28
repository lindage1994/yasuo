package com.iahsnil.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author: zed
 * @Date: 2019/11/2 14:14
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
public class MusicApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }
}
