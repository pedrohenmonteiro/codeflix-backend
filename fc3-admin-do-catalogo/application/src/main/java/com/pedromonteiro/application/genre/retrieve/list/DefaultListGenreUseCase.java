package com.pedromonteiro.application.genre.retrieve.list;

import java.util.Objects;

import com.pedromonteiro.domain.genre.GenreGateway;
import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.domain.pagination.SearchQuery;

public class DefaultListGenreUseCase extends ListGenreUseCase {
    
    private final GenreGateway genreGateway;

    public DefaultListGenreUseCase(final GenreGateway genreGateway) {
        this.genreGateway = Objects.requireNonNull(genreGateway);
    }

    @Override
    public Pagination<GenreListOutput> execute(final SearchQuery aQuery) {
        return this.genreGateway.findAll(aQuery)
                .map(GenreListOutput::from);
    }
}