package com.github.mateuszwenus.service;

import java.util.UUID;

public record UpdateTodoCmd (UUID id, String title, String text) {
}
