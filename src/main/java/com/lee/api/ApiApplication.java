package com.lee.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        System.setProperty("reactor.netty.http.server.accessLogEnabled","true");
        SpringApplication.run(ApiApplication.class, args);
    }

}
