package com.pedromonteiro.domain.event;

@FunctionalInterface
public interface DomainEventPublisher {
     void publishEvent(DomainEvent event);
    
}
