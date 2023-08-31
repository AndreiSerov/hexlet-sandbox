package org.example;

import org.example.persist.MyRepository;
import org.example.persist.Repository;
import org.example.service.JdkService;
import org.example.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Main {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MyService myService;

    @Autowired
    private JdkService jdkService;

    @Autowired
    private Repository repo;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        log.info("Object class : "+ myService.getClass().getName());
//        log.info("Object class : "+ jdkService.getClass().getName());
        log.info("Object class : "+ repo.getClass().getName());

        repo.methodTransactional();
        repo.method();

        return args -> {
            myService.method1();
        };
    }
}