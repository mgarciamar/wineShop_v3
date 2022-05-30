package com.example.wineshop;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface WineRepository extends JpaRepository<Wine, Long>{

    //Optional<Wine> findByNameEquals(String name);
    @Query(value="SELECT * FROM wine order by rating desc LIMIT ?1 ", nativeQuery = true)
    Collection<Wine> findBestWines(Integer top);

    @Query(value="SELECT * FROM wine order by price desc LIMIT ?1 ", nativeQuery = true)
    Collection<Wine> findExpensiveWines(Integer top);

    @Query(value="SELECT * FROM wine order by rating/price desc LIMIT ?1 ", nativeQuery = true)
    Collection<Wine> findBangWines(Integer top);

    @Query(value="select * from wine group by year order by avg(rating) desc, year LIMIT ?1 ", nativeQuery = true)
    Collection<Wine> findVintageWines(Integer top);


}
