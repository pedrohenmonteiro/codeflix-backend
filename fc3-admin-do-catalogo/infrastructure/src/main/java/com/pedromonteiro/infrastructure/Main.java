package com.pedromonteiro.infrastructure;


import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;

import com.pedromonteiro.application.category.create.CreateCategoryUseCase;
import com.pedromonteiro.infrastructure.configuration.WebServerConfig;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "development");
        SpringApplication.run(WebServerConfig.class,args);
    }

    @Bean
    @DependsOnDatabaseInitialization
    ApplicationRunner runner(CreateCategoryUseCase createCategoryUseCase) {
        return args -> {

        };
    }
}