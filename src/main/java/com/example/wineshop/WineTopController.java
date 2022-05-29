package com.example.wineshop;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class WineTopController {

    private final WineRepository repository;
    WineTopController(WineRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/recommend/best")
    Collection<Wine> best(@RequestParam(name="top", required=true, defaultValue="10") Integer top) {
        return repository.findBestWines(top);
    }

    @GetMapping("/recommend/expensive")
    Collection<Wine> expensive(@RequestParam(name="top", required=true, defaultValue="10") Integer top) {
        return repository.findExpensiveWines(top);
    }

    @GetMapping("/recommend/bang")
    Collection<Wine> bang(@RequestParam(name="top", required=true, defaultValue="10") Integer top) {
        return repository.findBangWines(top);
    }

    @GetMapping("/recommend/vintage")
    Collection<Wine> vintage(@RequestParam(name="top", required=true, defaultValue="10") Integer top) {
        return repository.findVintageWines(top);
    }

}
