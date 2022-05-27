package com.example.wineshop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

//lanzar pruebas en un puerto random
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class WineryControllerTest {

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
            .uri("/winery")
            .exchange() //recupera la respuesta
            .expectStatus().isOk()
            .expectHeader().valueEquals("Content-Type", "application/hal+json")
            .expectBody()
            .jsonPath("$._embedded.wineryList.size()").isEqualTo(483)
            .jsonPath("$._embedded.wineryList[1].name").isEqualTo("Artadi") //nombre del winery
            .jsonPath("$._embedded.wineryList[1].id").isEqualTo(2); //id

    }

    @Test
    void one() {
        webTestClient.get()
                .uri("/winery/{id}", 3)
                .exchange()//recupera la respuesta
                .expectStatus().isOk()//
                .expectHeader().valueEquals("Content-Type", "application/hal+json")
                .expectBody()
                .jsonPath("$.name").isEqualTo("Vega Sicilia") //nombre
                .jsonPath("$.id").isEqualTo(3); //id
    }


    @Test
    void wineryDoesntExist() {
        webTestClient.get()

                .uri("/winery/{id}", 600)
                .exchange()//recupera la respuesta
                //.expectStatus().is4xxClientError()//404
                .expectStatus().isNotFound()
                .expectBody()
                .toString().contains("Could not find winery");
    }


    @Test
    void updateWinery() {

        Winery winery = new Winery();
        winery.setName("Bodega Test");

        webTestClient.put()
                .uri("/winery/{id}", 4)
                .bodyValue(winery)
                .exchange()//recupera la respuesta
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Bodega Test"); //nombre

    }

    @Test
    void createWinery() {

        Winery winery = new Winery();
        winery.setName("Winery Test");

        webTestClient.post()
                .uri("/winery")
                .bodyValue(winery)
                .exchange()//recupera la respuesta
                .expectStatus().is2xxSuccessful()//
                .expectBody()
                .jsonPath("$.name").isEqualTo("Winery Test"); //nombre
    }


    @Test
    void deleteWinery() {
        webTestClient.delete()

                .uri("/winery/{id}", 15)
                .exchange()//recupera la respuesta
                .expectStatus().is2xxSuccessful();
    }

    @Test
    void deleteWineryDoesntExists() {
        webTestClient.delete()

                .uri("/winery/{id}", 600)
                .exchange()//recupera la respuesta
                .expectStatus().is5xxServerError();//Internal server error

    }

}




