package com.pedromonteiro.domain.category;

import java.time.Instant;
import java.util.Objects;

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
        this.createdAt = Objects.requireNonNull(aCreationDate, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(aUpdateDate, "'updatedAt' should not be null");
        this.deletedAt = aDeleteDate;
    }

    public static Category newCategory(
        final String aName,
        final String aDescription,
        final Boolean isActive
    ) {
        final var id = CategoryID.unique();
        final var now = Instant.now();
        final var deletedAt = isActive ? null : now;
        return new Category(id, aName, aDescription, isActive, now, now, deletedAt);
    }

    public static Category with(
        final CategoryID anId,
        final String name,
        final String description,
        final boolean active,
        final Instant createdAt,
        final Instant updatedAt,
        final Instant deletedAt
) {
    return new Category(
        anId,
        name,
        description,
        active,
        createdAt,
        updatedAt,
        deletedAt
    );
}

    public static Category clone(Category aCategory) {
        return with(
            aCategory.getId(),
            aCategory.name,
            aCategory.description,
            aCategory.isActive(),
            aCategory.createdAt,
            aCategory.updatedAt,
            aCategory.deletedAt
        );
    }

   

    @Override
    public void validate(ValidationHandler handler) {

        new CategoryValidator(this, handler).validate();
    }

    public Category deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }

        this.active = false;
        this.updatedAt = Instant.now();

        return this;
    }

    public Category activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = Instant.now();

        return this;
    }

    public Category update(final String aName, final String aDescription, final boolean isActive) {
        if(isActive) {
            activate();
        } else {
            deactivate();
        }

        this.name = aName;
        this.description = aDescription;
        this.updatedAt = Instant.now();
        return this;
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