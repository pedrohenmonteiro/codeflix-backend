package com.pedromonteiro.infrastructure.services;

import java.util.List;
import java.util.Optional;

import com.pedromonteiro.domain.video.Resource;

public interface StorageService {

    void store(String id, Resource resource);

    Optional<Resource> get(String id);

    List<String> list(String prefix);

    void deleteAll(final List<String> ids);
}
