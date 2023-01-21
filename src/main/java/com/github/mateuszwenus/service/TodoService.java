package com.github.mateuszwenus.service;

import com.github.mateuszwenus.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TodoService {

    private List<Todo> todos = new ArrayList<>();

    public Todo findById(UUID id) {
        return todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TodoNotFoundException());
    }

    public List<Todo> findAll() {
        return todos;
    }

    public Todo createTodo(CreataTodoCmd cmd) {
        Todo todo = new Todo();
        todo.setId(UUID.randomUUID());
        todo.setTitle(cmd.title());
        todo.setText(cmd.text());
        todos.add(todo);
        return todo;
    }

    public Todo updateTodo(UpdateTodoCmd cmd) {
        Todo todo = findById(cmd.id());
        todo.setTitle(cmd.title());
        todo.setText(cmd.text());
        return todo;
    }

    public void deleteTodo(DeleteTodoCmd cmd) {
        todos.removeIf(todo -> todo.getId().equals(cmd.id()));
    }

    public void deleteAll() {
        todos.clear();
    }
}
