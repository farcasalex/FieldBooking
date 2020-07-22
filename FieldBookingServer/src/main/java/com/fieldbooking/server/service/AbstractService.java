package com.fieldbooking.server.service;

import java.util.List;
import java.util.Optional;

public interface AbstractService<Entity> {
    Entity save(Entity entity);

    List<Entity> findAll();

    Optional<Entity> findById(Long id);

    void delete(Long id);
}
