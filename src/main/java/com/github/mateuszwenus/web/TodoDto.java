package com.github.mateuszwenus.web;

import com.github.mateuszwenus.entity.Todo;

import java.util.UUID;

public record TodoDto(UUID id, String title, String text) {

    public TodoDto(Todo input) {
        this(input.getId(), input.getTitle(), input.getText());
    }
}
