package com.pedromonteiro.infrastructure.category.presenters;

import com.pedromonteiro.application.category.retrieve.get.CategoryOutput;
import com.pedromonteiro.infrastructure.category.models.CategoryResponse;

public interface CategoryApiPresenter {

    static CategoryResponse present(final CategoryOutput output) {
        return new CategoryResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }
}