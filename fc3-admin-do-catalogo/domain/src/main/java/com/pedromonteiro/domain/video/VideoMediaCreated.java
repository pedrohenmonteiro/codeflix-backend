package com.pedromonteiro.domain.video;

import java.time.Instant;

import com.pedromonteiro.domain.event.DomainEvent;
import com.pedromonteiro.domain.utils.InstantUtils;

public record VideoMediaCreated(
        String resourceId,
        String filePath,
        Instant occurredOn
) implements DomainEvent {

    public VideoMediaCreated(final String resourceId, final String filePath) {
        this(resourceId, filePath, InstantUtils.now());
    }
}
