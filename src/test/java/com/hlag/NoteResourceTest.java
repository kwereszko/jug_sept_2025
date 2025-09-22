package com.hlag;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class NoteResourceTest {
    @Test
    void shouldReturnEmpty_whenList() {
        // Verify the list is initially empty
        given()
            .when().get("/notes")
            .then()
            .statusCode(200)
            .body(is("[]"));
    }

    @Test
    void shouldReturnCreatedNote_whenGet_givenIdOfCreatedNote() {
        // Add a new note
        String noteJson = "{\"title\":\"Test Note\",\"content\":\"This is a test note.\",\"type\":\"NORMAL\"}";
        given()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .body(noteJson)
            .when().post("/notes")
            .then()
            .statusCode(200)
            .body("title", is("Test Note"))
            .body("content", is("This is a test note."))
            .body("type", is("NORMAL"));

        // Retrieve the added note by ID
        given()
            .when().get("/notes/1")
            .then()
            .statusCode(200)
            .body("title", is("Test Note"))
            .body("content", is("This is a test note."));
    }

}