package com.example.wineshop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.lang.NonNull;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Entity
@Table(name = "wine")
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    //@NonNull
    @NotNull(message = "Id no puede ser nulo")
    private  Long id;

    @NotNull
    private int winery_id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String name;

    @NotNull
    @Range(min=1900, max=2022, message = "El año debe estar entre [1900,2022]")
    private String year;

    @NotNull
    @Range(min=0, message = "El valor de num reviews no puede ser negativo")
    private int num_reviews;

    @NotNull
    @Range(min=0, max=5, message = "El valor de rating debe estar entre [0,5]")
    private float rating;

    @NotNull
    private int region_id;

    @NotNull
    @Range(min=0, message = "El valor de price no puede ser negativo")
    private double price;

    @NotNull
    private int type_id;

    @NotNull
    @Range(min=1, max=5, message = "El valor de body debe estar entre [1,5]")
    private String body;

    @NotNull
    @Range(min=1, max=5, message = "El valor de acidity debe estar entre [1,5]")
    private String acidity;

/*

    @NotNull
    private @Id @GeneratedValue  Long id;



    private @ManyToOne @JoinColumn(name = "winery") Winery winery;

    @NotNull
    @Range(min=1900, max=2022)
    private int year;

    @NotNull
    @Range(min=0)
    private double num_reviews;

    @NotNull
    private @ManyToOne @JoinColumn(name = "region") Region region;

    @NotNull
    @Range(min=0)
    private double price;

    @NotNull
    private @ManyToOne @JoinColumn(name = "type") Type type;

    @NotNull
    @Range(min=1, max=5)
    private int body;

    @NotNull
    @Range(min=1, max=5)
    private int acidity;

    @NotNull
    @Range(min=0, max=5)
    private double rating;

    @NotEmpty
    private String name;
 */

    Wine(){}

    public Wine(Long id, int winery_id, String name, String year, int num_reviews, float rating, int region_id, double price, int type_id, String body, String acidity) {
        this.id = id;
        this.winery_id = winery_id;
        this.name = name;
        this.year = year;
        this.num_reviews = num_reviews;
        this.rating = rating;
        this.region_id = region_id;
        this.price = price;
        this.type_id = type_id;
        this.body = body;
        this.acidity = acidity;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getWinery_id() {
        return winery_id;
    }

    public void setWinery_id(int winery_id) {
        this.winery_id = winery_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getNum_reviews() {
        return num_reviews;
    }

    public void setNum_reviews(int num_reviews) {
        this.num_reviews = num_reviews;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAcidity() {
        return acidity;
    }

    public void setAcidity(String acidity) {
        this.acidity = acidity;
    }

    //REVISAR
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wine wine1 = (Wine) o;
        return num_reviews == wine1.num_reviews && Double.compare(wine1.price, price) == 0 && Objects.equals(id, wine1.id) && Objects.equals(winery_id, wine1.winery_id) && Objects.equals(name, wine1.name) && Objects.equals(year, wine1.year) && Objects.equals(rating, wine1.rating) && Objects.equals(region_id, wine1.region_id) && Objects.equals(type_id, wine1.type_id) && Objects.equals(body, wine1.body) && Objects.equals(acidity, wine1.acidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, winery_id, name, year, num_reviews, rating, region_id, price, type_id, body, acidity);
    }

    @Override
    public String toString() {
        return "Wine{" +
                "id=" + id +
                ", winery='" + winery_id + '\'' +
                ", wine='" + name + '\'' +
                ", year='" + year + '\'' +
                ", num_reviews=" + num_reviews +
                ", country='" + rating + '\'' +
                ", region='" + region_id + '\'' +
                ", price=" + price +
                ", type='" + type_id + '\'' +
                ", body='" + body + '\'' +
                ", acidity='" + acidity + '\'' +
                '}';
    }
}
