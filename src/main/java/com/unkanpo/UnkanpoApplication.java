package com.unkanpo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnkanpoApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(UnkanpoApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
    }

}
