package ch.springboot.fundamentals.pizzaservice;

import ch.springboot.fundamentals.pizzaservice.infrastructure.CatalogEntry;
import ch.springboot.fundamentals.pizzaservice.infrastructure.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataCreator implements CommandLineRunner {

    @Autowired
    private CatalogRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new CatalogEntry("Salami", 0));
        repository.save(new CatalogEntry("Funghi", 4));
    }
}
