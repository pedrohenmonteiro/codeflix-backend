package com.pedromonteiro.application.category.retrieve.get;

import java.time.Instant;

import com.pedromonteiro.domain.category.Category;
import com.pedromonteiro.domain.category.CategoryID;

public record CategoryOutput(
CategoryID id,
String name,
String description,
boolean isActive,
Instant createdAt,
Instant updatedAt,
Instant deletedAt
) {
    
    public static CategoryOutput from(final Category aCategory) {
        return new CategoryOutput(
            aCategory.getId(),
            aCategory.getName(),
            aCategory.getDescription(),
            aCategory.isActive(),
            aCategory.getCreatedAt(),
            aCategory.getUpdatedAt(),
            aCategory.getDeletedAt()
        );
    }
}
