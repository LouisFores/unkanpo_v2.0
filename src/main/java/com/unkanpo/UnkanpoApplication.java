package com.unkanpo;

import com.unkanpo.model.Type;
import com.unkanpo.service.IGameService;
import com.unkanpo.service.imp.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class UnkanpoApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(UnkanpoApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
    }

}
