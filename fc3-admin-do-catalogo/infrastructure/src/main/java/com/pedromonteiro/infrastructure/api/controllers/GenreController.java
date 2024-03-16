package com.pedromonteiro.infrastructure.api.controllers;

import org.springframework.http.ResponseEntity;

import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.infrastructure.api.GenreAPI;
import com.pedromonteiro.infrastructure.genre.models.CreateGenreRequest;
import com.pedromonteiro.infrastructure.genre.models.GenreListResponse;
import com.pedromonteiro.infrastructure.genre.models.GenreResponse;
import com.pedromonteiro.infrastructure.genre.models.UpdateGenreRequest;

public class GenreController implements GenreAPI {

    @Override
    public ResponseEntity<?> create(final CreateGenreRequest input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Pagination<GenreListResponse> list(
        final String search,
        final int page,
        final int perPage,
        final String sort,
        final String direction
        ) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    @Override
    public GenreResponse getById(final String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateGenreRequest input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateById'");
    }

    @Override
    public void deleteById(final String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
    
}
