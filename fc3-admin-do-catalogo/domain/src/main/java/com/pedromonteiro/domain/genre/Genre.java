package com.pedromonteiro.domain.genre;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pedromonteiro.domain.AggregateRoot;
import com.pedromonteiro.domain.category.CategoryID;
import com.pedromonteiro.domain.exceptions.NotificationException;
import com.pedromonteiro.domain.utils.InstantUtils;
import com.pedromonteiro.domain.validation.ValidationHandler;
import com.pedromonteiro.domain.validation.handler.Notification;

public class Genre extends AggregateRoot<GenreID> {

    private String name;
    private boolean active;
    private List<CategoryID> categories;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

   

    protected Genre(
        final GenreID id,
        final String name,
        final boolean active,
        final List<CategoryID> categories, 
        final Instant createdAt,
        final Instant updatedAt,
        final Instant deletedAt
        ) {
        super(id);
        this.name = name;
        this.active = active;
        this.categories = categories;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        selfValidate();
    }

    public static Genre newGenre(
        final String aName,
        final boolean isActive
    )  {
        final var anId = GenreID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = isActive ? null : now;
        return new Genre(anId, aName, isActive, new ArrayList<>(), now, now, deletedAt);
    }


    public static Genre with(
        final GenreID anId,
        final String aName,
        final boolean isActive,
        final List<CategoryID> categories, 
        final Instant aCreatedAt,
        final Instant aUpdatedAt,
        final Instant aDeletedAt
        ) {
        return new Genre(anId, aName, isActive, categories, aCreatedAt, aUpdatedAt, aDeletedAt);
    }

    public static Genre with(final Genre aGenre) {
        return new Genre(
            aGenre.id,
            aGenre.name,
            aGenre.active,
            new ArrayList<>(aGenre.categories),
            aGenre.createdAt,
            aGenre.updatedAt,
            aGenre.deletedAt
        );
    }

    @Override
    public void validate(ValidationHandler handler) {
        new GenreValidator(this, handler).validate();
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);

        if (notification.hasError()) {
            throw new NotificationException("Failed to create a Aggregate Genre", notification);
        }
    }

    public Genre update(final String aName, final boolean isActive, final List<CategoryID> categories) {
        if (isActive) {
            activate();
        } else {
            deactivate();
        }
        this.name = aName;
        this.categories = new ArrayList<>(categories != null ? categories : Collections.emptyList());
        this.updatedAt = InstantUtils.now();
        selfValidate();
        return this;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public List<CategoryID> getCategories() {
        return Collections.unmodifiableList(categories);
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

    public Genre deactivate() {
        if (getDeletedAt() == null) {this.deletedAt = InstantUtils.now();}
        this.active = false;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Genre activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
        return this;
    }
    
    public Genre addCategory(final CategoryID aCategoryID) {
        if (aCategoryID == null) {
            return this;
        }
        this.categories.add(aCategoryID);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Genre removeCategory(final CategoryID aCategoryID) {
        if (aCategoryID == null) {
            return this;
        }
        this.categories.remove(aCategoryID);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Genre addCategories(final List<CategoryID> categories) {
        if (categories == null || categories.isEmpty()) {
            return this;
        }
        this.categories.addAll(categories);
        this.updatedAt = InstantUtils.now();
        return this;
    }
}
