package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class EntryResourceTest {

    //Testdaten von ChatGPT

    @Test
public void testCreateEntries() {
    // Create Entry 1
    given()
        .contentType("application/json")
        .body("{\"checkIn\": \"2023-01-01T08:00:00\", \"checkOut\": \"2023-01-01T16:00:00\"}")
    .when()
        .post("/entries")
    .then()
        .statusCode(200); 

    // Create Entry 2
    given()
        .contentType("application/json")
        .body("{\"checkIn\": \"2023-01-02T09:00:00\", \"checkOut\": \"2023-01-02T17:00:00\"}")
    .when()
        .post("/entries")
    .then()
        .statusCode(200);

    // Create Entry 3
    given()
        .contentType("application/json")
        .body("{\"checkIn\": \"2023-01-03T10:00:00\", \"checkOut\": \"2023-01-03T18:00:00\"}")
    .when()
        .post("/entries")
    .then()
        .statusCode(200);
}
    
    @Test
    public void testIndexEndpoint() {
        given()
          .when().get("/entries")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }


    @Test
    public void testDeleteEndpoint() {
        given()
            .when()
            .delete("/entries/{id}", 1)
        .then()
            .statusCode(204);
    }
    @Test
    public void testEditEntryEndpoint() {
        given()
            .contentType("application/json")
            .body("{\"checkIn\": \"2023-01-01T08:00:00\", \"checkOut\": \"2023-01-01T16:00:00\"}")
            .when()
            .put("/entries/{id}", 2)
        .then()
            .statusCode(200);
}

}