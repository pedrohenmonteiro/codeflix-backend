package com.pedromonteiro.domain.video;

import java.util.Objects;
import java.util.UUID;

import com.pedromonteiro.domain.Identifier;

public class VideoID extends Identifier {
    private final String value;

    private VideoID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

      public static VideoID unique() {
        return VideoID.from(UUID.randomUUID());
    }

    public static VideoID from(final String anId) {
        return new VideoID(anId);
    }

     public static VideoID from(final UUID anId) {
        return new VideoID(anId.toString().toLowerCase());
    }
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final VideoID that = (VideoID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
