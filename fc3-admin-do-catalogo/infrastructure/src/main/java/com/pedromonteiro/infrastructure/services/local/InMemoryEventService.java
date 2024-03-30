package com.pedromonteiro.infrastructure.services.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pedromonteiro.infrastructure.configuration.json.Json;
import com.pedromonteiro.infrastructure.services.EventService;

public class InMemoryEventService implements EventService {

    private static final Logger LOG = LoggerFactory.getLogger(InMemoryEventService.class);

    @Override
    public void send(Object event) {
        LOG.info("Event was observed: {}", Json.writeValueAsString(event));
    }
}
