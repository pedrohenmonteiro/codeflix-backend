package com.pedromonteiro.application.category.retrieve.list;

import java.time.Instant;

import com.pedromonteiro.domain.category.Category;
import com.pedromonteiro.domain.category.CategoryID;


public record CategoryListOutput(
    CategoryID id,
    String name,
    String description,
    boolean isActive,
    Instant createdAt,
    Instant deletedAt
) {
    
    public static CategoryListOutput from(final Category aCategory) {
        return new CategoryListOutput(
            aCategory.getId(),
            aCategory.getName(),
            aCategory.getDescription(),
            aCategory.isActive(),
            aCategory.getCreatedAt(),
            aCategory.getDeletedAt()
        );
    }
}
