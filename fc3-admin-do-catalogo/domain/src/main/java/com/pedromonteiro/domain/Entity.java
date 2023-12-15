package com.pedromonteiro.domain;

import java.util.Objects;

import com.pedromonteiro.domain.validation.ValidationHandler;

public abstract class Entity<ID extends Identifier> {
    
    protected final ID id;

    public Entity(final ID id) {
        Objects.requireNonNull(id, "'id' is required");
        this.id = id;
    }

    public abstract void validate(ValidationHandler handler); 

    public ID getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Entity<?> other = (Entity<?>) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
