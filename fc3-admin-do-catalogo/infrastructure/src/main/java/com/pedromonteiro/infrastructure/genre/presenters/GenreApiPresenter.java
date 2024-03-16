package com.pedromonteiro.infrastructure.genre.presenters;

import com.pedromonteiro.application.genre.retrieve.get.GenreOutput;
import com.pedromonteiro.application.genre.retrieve.list.GenreListOutput;
import com.pedromonteiro.infrastructure.genre.models.GenreListResponse;
import com.pedromonteiro.infrastructure.genre.models.GenreResponse;

public interface GenreApiPresenter {

    static GenreResponse present(final GenreOutput output) {
        return new GenreResponse(
                output.id(),
                output.name(),
                output.categories(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static GenreListResponse present(final GenreListOutput output) {
        return new GenreListResponse(
                output.id(),
                output.name(),
                output.isActive(),
                output.createdAt(),
                output.deletedAt()
        );
    }
}