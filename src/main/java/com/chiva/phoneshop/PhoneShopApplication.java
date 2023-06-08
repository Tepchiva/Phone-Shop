package com.chiva.phoneshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PhoneShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner usage() {
        return args -> {
            System.out.println("Start spring boot application...!");
        };
    }
}
