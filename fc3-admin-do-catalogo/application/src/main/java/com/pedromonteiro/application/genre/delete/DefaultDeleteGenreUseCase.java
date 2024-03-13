package com.pedromonteiro.application.genre.delete;

import java.util.Objects;

import com.pedromonteiro.domain.genre.GenreGateway;
import com.pedromonteiro.domain.genre.GenreID;

public class DefaultDeleteGenreUseCase extends DeleteGenreUseCase {

    private final GenreGateway genreGateway;

    

    public DefaultDeleteGenreUseCase(GenreGateway genreGateway) {
        this.genreGateway = Objects.requireNonNull(genreGateway);
    }



    @Override
    public void execute(String anIn) {
       
        this.genreGateway.deleteById(GenreID.from(anIn));
    }
    
}
