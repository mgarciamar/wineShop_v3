/*package com.example.wineshop;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase  {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(WineRepository repository, WineryRepository wineryRepository, TypeRepository typeRepository) {

        return args -> {
            log.info("Preloading Winery" + wineryRepository.save(new Winery(1L,"Vega Sicilia Test")));
            log.info("Preloading Type" + typeRepository.save(new Type(1L,"Tempranillo Test")));
        };
    }

}
*/
