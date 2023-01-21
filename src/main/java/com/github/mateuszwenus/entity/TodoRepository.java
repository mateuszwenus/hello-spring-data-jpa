package com.github.mateuszwenus.entity;

import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface TodoRepository extends ListCrudRepository<Todo, UUID> {
}
