package com.pedromonteiro.application.genre.update;

import com.pedromonteiro.domain.genre.Genre;

public record UpdateGenreOutput(String id) {
    
    public static UpdateGenreOutput from(Genre aGenre) {
        return new UpdateGenreOutput(aGenre.getId().getValue());
    }
}
