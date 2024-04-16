package com.unkanpo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class UnkanpoApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(UnkanpoApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {}

}
