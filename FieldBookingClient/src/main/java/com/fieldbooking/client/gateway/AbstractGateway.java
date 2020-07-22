package com.fieldbooking.client.gateway;

import java.util.List;
import java.util.Optional;

public interface AbstractGateway<Entity> {
    Entity save(Entity entity);

    List<Entity> findAll();

    Optional<Entity> findById(Long id);

    void delete(Long id);
}
