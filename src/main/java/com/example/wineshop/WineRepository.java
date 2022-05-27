package com.example.wineshop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WineRepository extends JpaRepository<Wine, Long>{

    //Optional<Wine> findByNameEquals(String name);

}
