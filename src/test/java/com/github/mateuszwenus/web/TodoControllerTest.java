package com.github.mateuszwenus.web;

import com.github.mateuszwenus.entity.Todo;
import com.github.mateuszwenus.service.CreateTodoCmd;
import com.github.mateuszwenus.service.TodoService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    TodoService todoService;

    @BeforeEach
    public void beforeTest() {
        todoService.deleteAll();
    }

    @Test
    public void shouldReturnEmptyList() {
        given()
                .accept(ContentType.JSON)
                .log()
                .all(true)
        .when()
                .get("http://localhost:" + port + "/todos")
        .then()
                .log()
                .all(true)
                .and()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body(is("[]"));
    }

    @Test
    public void shouldReturnHttp404WhenTodoNotFound() {
        given()
                .accept(ContentType.JSON)
                .log()
                .all(true)
        .when()
                .get("http://localhost:" + port + "/todos/" + UUID.randomUUID())
        .then()
                .log()
                .all(true)
                .and()
                .statusCode(is(404));
    }

    @Test
    public void shouldCreateNewTodoItem() {
        CreateTodoRequest req = new CreateTodoRequest("title", "text");
        given()
                .contentType(ContentType.JSON)
                .body(req)
                .accept(ContentType.JSON)
                .log()
                .all(true)
        .when()
                .post("http://localhost:" + port + "/todos")
        .then()
                .log()
                .all(true)
                .and()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("title", is(req.getTitle()))
                .body("text", is(req.getText()));
    }

    @Test
    public void shouldReturnListWithCreatedTodoItem() {
        Todo todo = todoService.createTodo(new CreateTodoCmd("title", "text"));
        given()
                .accept(ContentType.JSON)
                .log()
                .all(true)
        .when()
                .get("http://localhost:" + port + "/todos")
        .then()
                .log()
                .all(true)
                .and()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("[0].id", is(todo.getId().toString()))
                .body("[0].title", is(todo.getTitle()));
    }

    @Test
    public void shouldReturnCreatedTodoItem() {
        Todo todo = todoService.createTodo(new CreateTodoCmd("title", "text"));
        given()
                .accept(ContentType.JSON)
                .log()
                .all(true)
        .when()
                .get("http://localhost:" + port + "/todos/" + todo.getId())
        .then()
                .log()
                .all(true)
                .and()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("id", is(todo.getId().toString()))
                .body("title", is(todo.getTitle()))
                .body("text", is(todo.getText()));
    }

    @Test
    public void shouldUpdateTodoItem() {
        Todo todo = todoService.createTodo(new CreateTodoCmd("title", "text"));
        UpdateTodoRequest req = new UpdateTodoRequest("title2", "text2");
        given()
                .contentType(ContentType.JSON)
                .body(req)
                .accept(ContentType.JSON)
                .log()
                .all(true)
        .when()
                .put("http://localhost:" + port + "/todos/" + todo.getId())
        .then()
                .log()
                .all(true)
                .and()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("id", is(todo.getId().toString()))
                .body("title", is(req.getTitle()))
                .body("text", is(req.getText()));
    }
}
