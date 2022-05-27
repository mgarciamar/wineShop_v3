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
public class RegionControllerTest {

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
                .uri("/region")
                .exchange() //recupera la respuesta
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/hal+json")
                .expectBody()
                .jsonPath("$._embedded.regionList[0].id").isEqualTo(1)
                .jsonPath("$._embedded.regionList[0].name").isEqualTo("Toro") //nombre del primer vino
                .jsonPath("$._embedded.regionList[0].country").isEqualTo("Espana");
    }


    @Test
    void typeDoesnExists() {

        webTestClient.get()
                .uri("/region/{id}" , 9000)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .toString().contains("Could not find region");
    }


    @Test
    void updateRegion() {
        Region region = new Region();
        region.setName("Vallecas");
        region.setCountry("Espana");

        webTestClient.put()
                .uri("/region/{id}" , 1)
                .bodyValue(region)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Vallecas")
                .jsonPath("$.country").isEqualTo("Espana");
    }

    @Test
    void createRegion(){
        Region region = new Region();
        region.setName("Tarraco");
        region.setCountry("Espana");

        webTestClient.post()
                .uri("/region")
                .bodyValue(region)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Tarraco")
                .jsonPath("$.country").isEqualTo("Espana");
    }


    @Test
    void deleteRegion() {
        webTestClient.delete()
                .uri("/region/{id}", 2)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }


    @Test
    void deleteRegionDoesntExists(){
        webTestClient.delete()

                .uri("/region/{id}", 9000)
                .exchange()
                .expectStatus().is5xxServerError();
    }
}
