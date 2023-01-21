package com.github.mateuszwenus.web;

import com.github.mateuszwenus.entity.Todo;

import java.util.UUID;

public record TodoListDto(UUID id, String title) {

    public TodoListDto(Todo input) {
        this(input.getId(), input.getTitle());
    }
}
