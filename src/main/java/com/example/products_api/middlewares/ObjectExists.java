package com.example.products_api.middlewares;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ObjectExists {

    public <T, UUID> boolean exists(JpaRepository<T, UUID> repository, UUID id) {
        return repository.findById(id).isPresent();
    }
}

