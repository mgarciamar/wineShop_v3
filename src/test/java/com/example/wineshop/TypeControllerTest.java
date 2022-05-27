package com.example.wineshop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class TypeControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void all() {
        webTestClient.get()
                .uri("/type")
                .exchange() //recupera la respuesta
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/hal+json")
                .expectBody()
                .jsonPath("$._embedded.typeList[0].name").isEqualTo("Toro Red") //nombre del primer vino
                .jsonPath("$._embedded.typeList[0].id").isEqualTo(1); //id

    }

    @Test
    void typeDoesntExists() {
        webTestClient.get()

                .uri("/type/{id}", 8000)
                .exchange()//recupera la respuesta
                //.expectStatus().is4xxClientError()//404
                .expectStatus().isNotFound()
                .expectBody()
                .toString().contains("Could not find type");
    }

    @Test
    void updateType() {
        Type type = new Type();
        type.setName("Alejandro");

        webTestClient.put()
                .uri("/type/{id}", 1)
                .bodyValue(type)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Alejandro");

    }

    @Test
    void createType() {
        Type type = new Type();
        type.setName("NuevoTipo");

        webTestClient.post()
                .uri("/type")
                .bodyValue(type)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("NuevoTipo");
    }

    @Test
    void deleteType() {
        webTestClient.delete()
                .uri("/type/{id}", 42)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }


    @Test
    void deleteTypeDoesntExists(){
        webTestClient.delete()

                .uri("/type/{id}", 9000)
                .exchange()
                .expectStatus().is5xxServerError();
    }




}
