package com.github.mateuszwenus.service;

import com.github.mateuszwenus.entity.Todo;
import com.github.mateuszwenus.entity.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Transactional
    public Todo findById(UUID id) {
        return todoRepository
                .findById(id)
                .orElseThrow(() -> new TodoNotFoundException());
    }

    @Transactional
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Transactional
    public Todo createTodo(CreataTodoCmd cmd) {
        Todo todo = new Todo();
        todo.setTitle(cmd.title());
        todo.setText(cmd.text());
        todoRepository.save(todo);
        return todo;
    }

    @Transactional
    public Todo updateTodo(UpdateTodoCmd cmd) {
        Todo todo = findById(cmd.id());
        todo.setTitle(cmd.title());
        todo.setText(cmd.text());
        return todo;
    }

    @Transactional
    public void deleteTodo(DeleteTodoCmd cmd) {
        todoRepository.deleteById(cmd.id());
    }

    @Transactional
    public void deleteAll() {
        todoRepository.deleteAll();
    }
}
