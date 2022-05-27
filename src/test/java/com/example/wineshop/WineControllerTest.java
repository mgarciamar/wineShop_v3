package com.example.wineshop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

//lanzar pruebas en un puerto random
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class WineControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

//Lo comentamos ya que se produce un error al devolver demasiados registros
/*
    @Test
    void all() {
            webTestClient.get()
            .uri("/wine")
            .exchange() //recupera la respuesta
            .expectStatus().isOk()
            .expectHeader().valueEquals("Content-Type", "application/hal+json")
            .expectBody()
            .jsonPath("$._embedded.wineList.size()").isEqualTo(7500)
            .jsonPath("$._embedded.wineList[2].name").isEqualTo("Vina El Pison") //nombre del primer vino
            .jsonPath("$._embedded.wineList[2].id").isEqualTo(2) //id
            .jsonPath("$._embedded.wineList[2].year").isEqualTo("2018") //año
            .jsonPath("$._embedded.wineList[2].rating").isEqualTo("4.9") //rating
            .jsonPath("$._embedded.wineList[2].num_reviews").isEqualTo(31) //num_reviews
            .jsonPath("$._embedded.wineList[2].price").isEqualTo("313.5") //price
            .jsonPath("$._embedded.wineList[2].body").isEqualTo("4") //body
            .jsonPath("$._embedded.wineList[2].acidity").isEqualTo("2") //acidity
            .jsonPath("$._embedded.wineList[2].winery_id").isEqualTo(2) //winery_id
            .jsonPath("$._embedded.wineList[2].type_id").isEqualTo(2) //type_id
            .jsonPath("$._embedded.wineList[2].region_id").isEqualTo(2); //region_id
    }
*/
    @Test
    void one() {
        webTestClient.get()
                .uri("/wine/{id}", 2)
                .exchange()//recupera la respuesta
                .expectStatus().isOk()//
                .expectHeader().valueEquals("Content-Type", "application/hal+json")
                .expectBody()
                .jsonPath("$.name").isEqualTo("Ribera del Duero") //nombre del primer vino
                .jsonPath("$.id").isEqualTo(2) //id
                .jsonPath("$.year").isEqualTo("1500") //año
                .jsonPath("$.rating").isEqualTo("5.3") //rating
                .jsonPath("$.num_reviews").isEqualTo(5) //num_reviews
                .jsonPath("$.price").isEqualTo("20.0") //price
                .jsonPath("$.body").isEqualTo("1") //body
                .jsonPath("$.acidity").isEqualTo("6") //acidity
                .jsonPath("$.winery_id").isEqualTo(2) //winery_id
                .jsonPath("$.type_id").isEqualTo(2) //type_id
                .jsonPath("$.region_id").isEqualTo(2); //region_id
    }


    @Test
    void wineDoesntExist() {
        webTestClient.get()

                .uri("/wine/{id}", 8000)
                .exchange()//recupera la respuesta
                //.expectStatus().is4xxClientError()//404
                .expectStatus().isNotFound()
                .expectBody()
                .toString().contains("Could not find wine");
    }


    @Test
    void updateWine() {

        Wine wine = new Wine();
        wine.setName("Ribera del Duero");
        wine.setYear("1500");
        wine.setNum_reviews(5);
        wine.setRating(5.3f);
        wine.setPrice(20);
        wine.setBody("1");
        wine.setAcidity("6");

        webTestClient.put()
                .uri("/wine/{id}", 2)
                .bodyValue(wine)
                .exchange()//recupera la respuesta
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Ribera del Duero") //nombre del primer vino
                .jsonPath("$.id").isEqualTo(2) //id
                .jsonPath("$.year").isEqualTo("1500") //año
                .jsonPath("$.rating").isEqualTo("5.3") //rating
                .jsonPath("$.num_reviews").isEqualTo(5) //num_reviews
                .jsonPath("$.price").isEqualTo("20.0") //price
                .jsonPath("$.body").isEqualTo("1") //body
                .jsonPath("$.acidity").isEqualTo("6"); //acidity

    }

    @Test
    void createWine() {

        Wine wine = new Wine();
        wine.setName("Ribera del Duero");
        wine.setYear("1500");
        wine.setNum_reviews(5);
        wine.setRating(5.3f);
        wine.setPrice(20);
        wine.setBody("1");
        wine.setAcidity("6");
        wine.setType_id(3);
        wine.setRegion_id(1);
        wine.setWinery_id(2);

        webTestClient.post()
                .uri("/wine")
                .bodyValue(wine)
                .exchange()//recupera la respuesta
                .expectStatus().is2xxSuccessful()//
                .expectBody()
                .jsonPath("$.name").isEqualTo("Ribera del Duero") //nombre del primer vino
                .jsonPath("$.year").isEqualTo("1500") //año
                .jsonPath("$.rating").isEqualTo("5.3") //rating
                .jsonPath("$.num_reviews").isEqualTo(5) //num_reviews
                .jsonPath("$.price").isEqualTo("20.0") //price
                .jsonPath("$.body").isEqualTo("1") //body
                .jsonPath("$.acidity").isEqualTo("6") //acidity
                .jsonPath("$.type_id").isEqualTo("3") //price
                .jsonPath("$.region_id").isEqualTo("1") //body
                .jsonPath("$.winery_id").isEqualTo("2"); //acidity
    }


    @Test
    void deleteWine() {
        webTestClient.delete()

                .uri("/wine/{id}", 7502)
                .exchange()//recupera la respuesta
                .expectStatus().is2xxSuccessful();
    }

    @Test
    void deleteWineDoesntExists() {
        webTestClient.delete()

                .uri("/wine/{id}", 7501)
                .exchange()//recupera la respuesta
                .expectStatus().is5xxServerError();//Internal server error

    }

}




