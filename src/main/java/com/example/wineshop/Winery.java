package com.example.wineshop;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Winery")
public class Winery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private  Long id;

    private String name;

    public Winery(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Winery() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Winery winery = (Winery) o;
        return Objects.equals(id, winery.id) && Objects.equals(name, winery.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Winery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
