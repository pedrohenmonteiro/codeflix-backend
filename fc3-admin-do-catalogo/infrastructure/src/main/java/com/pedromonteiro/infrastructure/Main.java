package com.pedromonteiro.infrastructure;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

import com.pedromonteiro.infrastructure.configuration.WebServerConfig;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "sandbox");
       SpringApplication.run(WebServerConfig.class,args);
    }

    // @Bean
    // @DependsOnDatabaseInitialization
    // ApplicationRunner runner(CreateCategoryUseCase createCategoryUseCase) {
    //     return args -> {

    //     };
    // }
}