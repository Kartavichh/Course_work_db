package org.kartavich.application;

import org.kartavich.controller.MyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("org.kartavich.repository")
@EntityScan("org.kartavich.domain")
@ComponentScan("org.kartavich")
@EnableAutoConfiguration
public class Main implements CommandLineRunner {
    @Autowired
    MyController controller;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args){
        System.out.println("Hello");
        System.out.println(controller.help());
        // System.exit(0);
    }
}