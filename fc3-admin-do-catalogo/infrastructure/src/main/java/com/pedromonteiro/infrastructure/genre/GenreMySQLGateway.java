package com.pedromonteiro.infrastructure.genre;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.pedromonteiro.domain.genre.Genre;
import com.pedromonteiro.domain.genre.GenreGateway;
import com.pedromonteiro.domain.genre.GenreID;
import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.domain.pagination.SearchQuery;
import com.pedromonteiro.infrastructure.genre.persistence.GenreJpaEntity;
import com.pedromonteiro.infrastructure.genre.persistence.GenreRepository;

@Component
public class GenreMySQLGateway implements GenreGateway {

    private final GenreRepository genreRepository;

    

    public GenreMySQLGateway(GenreRepository genreRepository) {
        this.genreRepository = Objects.requireNonNull(genreRepository);
    }

    @Override
    public Genre create(Genre aGenre) {
        return save(aGenre);
    }

    @Override
    public void deleteById(GenreID anId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Optional<Genre> findById(GenreID anId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Genre update(Genre aGenre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Pagination<Genre> findAll(SearchQuery aQuery) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    
    private Genre save(final Genre aGenre) {
        return this.genreRepository.save(GenreJpaEntity.from(aGenre)).toAggregate();
    }
}
