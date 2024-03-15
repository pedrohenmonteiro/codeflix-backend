package com.pedromonteiro.infrastructure.configuration.usecases;

import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pedromonteiro.application.genre.create.CreateGenreUseCase;
import com.pedromonteiro.application.genre.create.DefaultCreateGenreUseCase;
import com.pedromonteiro.application.genre.delete.DefaultDeleteGenreUseCase;
import com.pedromonteiro.application.genre.delete.DeleteGenreUseCase;
import com.pedromonteiro.application.genre.retrieve.get.DefaultGetGenreByIdUseCase;
import com.pedromonteiro.application.genre.retrieve.get.GetGenreByIdUseCase;
import com.pedromonteiro.application.genre.retrieve.list.DefaultListGenreUseCase;
import com.pedromonteiro.application.genre.retrieve.list.ListGenreUseCase;
import com.pedromonteiro.application.genre.update.DefaultUpdateGenreUseCase;
import com.pedromonteiro.application.genre.update.UpdateGenreUseCase;
import com.pedromonteiro.domain.category.CategoryGateway;
import com.pedromonteiro.domain.genre.GenreGateway;

@Configuration
public class GenreUseCaseConfig {

    private final CategoryGateway categoryGateway;
    private final GenreGateway genreGateway;

    public GenreUseCaseConfig(
            final CategoryGateway categoryGateway,
            final GenreGateway genreGateway
    ) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
        this.genreGateway = Objects.requireNonNull(genreGateway);
    }

    @Bean
    public CreateGenreUseCase createGenreUseCase() {
        return new DefaultCreateGenreUseCase(genreGateway, categoryGateway);
    }

    @Bean
    public DeleteGenreUseCase deleteGenreUseCase() {
        return new DefaultDeleteGenreUseCase(genreGateway);
    }

    @Bean
    public GetGenreByIdUseCase getGenreByIdUseCase() {
        return new DefaultGetGenreByIdUseCase(genreGateway);
    }

    @Bean
    public ListGenreUseCase listGenreUseCase() {
        return new DefaultListGenreUseCase(genreGateway);
    }

    @Bean
    public UpdateGenreUseCase updateGenreUseCase() {
        return new DefaultUpdateGenreUseCase(genreGateway, categoryGateway);
    }
}