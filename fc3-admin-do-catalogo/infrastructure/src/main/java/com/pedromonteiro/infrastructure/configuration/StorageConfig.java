package com.pedromonteiro.infrastructure.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.cloud.storage.Storage;
import com.pedromonteiro.infrastructure.configuration.properties.google.GoogleStorageProperties;
import com.pedromonteiro.infrastructure.services.StorageService;
import com.pedromonteiro.infrastructure.services.impl.GCStorageService;
import com.pedromonteiro.infrastructure.services.local.InMemoryStorageService;

@Configuration
public class StorageConfig {

    @Bean
    @Profile({"development", "test-integration", "test-e2e"})
    public StorageService localStorageAPI() {
        return new InMemoryStorageService();
    }

    @Bean
    @ConditionalOnMissingBean
    public StorageService gcStorageAPI(
            final GoogleStorageProperties props,
            final Storage storage
    ) {
        return new GCStorageService(props.getBucket(), storage);
    }
}
