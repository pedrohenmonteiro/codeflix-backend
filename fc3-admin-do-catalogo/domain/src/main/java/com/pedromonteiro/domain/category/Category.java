package com.pedromonteiro.domain.category;

import java.time.Instant;

import com.pedromonteiro.domain.AggregateRoot;
import com.pedromonteiro.domain.validation.ValidationHandler;

public class Category extends AggregateRoot<CategoryID> {
    private String name;
    private String description;
    private Boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;


    private Category (
        final CategoryID anId,
        final String aName,
        final String aDescription,
        final Boolean isActive,
        final Instant aCreationDate,
        final Instant aUpdateDate,
        final Instant aDeleteDate
        ) {
        super(anId);
        this.name = aName;
        this.description = aDescription;
        this.active = isActive;
        this.createdAt = aCreationDate;
        this.updatedAt = aUpdateDate;
        this.deletedAt = aDeleteDate;
    }

    public static Category newCategory(
        final String aName,
        final String aDescription,
        final Boolean isActive
    ) {
        final var id = CategoryID.unique();
        final var now = Instant.now();
        return new Category(id, aName, aDescription, isActive, now, now, null);
    }


    @Override
    public void validate(ValidationHandler handler) {

        new CategoryValidator(this, handler).validate();
    }

    public CategoryID getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    
    public String getDescription() {
        return description;
    }

    

    public Boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

  

    public Instant getUpdatedAt() {
        return updatedAt;
    }

   
    public Instant getDeletedAt() {
        return deletedAt;
    }


    
    
}