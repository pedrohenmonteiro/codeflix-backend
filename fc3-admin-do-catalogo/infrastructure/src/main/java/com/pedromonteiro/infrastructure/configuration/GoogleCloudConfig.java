package com.pedromonteiro.infrastructure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pedromonteiro.infrastructure.configuration.properties.google.GoogleCloudProperties;
import com.pedromonteiro.infrastructure.configuration.properties.google.GoogleStorageProperties;

@Configuration
@Profile({"development", "production"})
public class GoogleCloudConfig {
    
    @Bean
    @ConfigurationProperties("google.cloud")
    public GoogleCloudProperties googleCloudProperties() {
        return new GoogleCloudProperties();
    }

    @Bean
    @ConfigurationProperties("google.")
    public GoogleStorageProperties googleStorageProperties() {
        return new GoogleStorageProperties();
    }
}
