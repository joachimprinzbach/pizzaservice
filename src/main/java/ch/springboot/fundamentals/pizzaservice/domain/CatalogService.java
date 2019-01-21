package ch.springboot.fundamentals.pizzaservice.domain;

import ch.springboot.fundamentals.pizzaservice.infrastructure.CatalogEntry;
import ch.springboot.fundamentals.pizzaservice.infrastructure.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.stream.StreamSupport;

@Service
public class CatalogService {

    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public boolean isEntryAvailable(String name) {
        Iterable<CatalogEntry> allEntries = catalogRepository.findAll();
        return StreamSupport.stream(allEntries.spliterator(), false)
                .filter(isInStock())
                .anyMatch(e -> e.getName().equals(name));
    }

    public Iterable<CatalogEntry> getAll() {
        return catalogRepository.findAll();
    }

    private Predicate<CatalogEntry> isInStock() {
        return entry -> entry.getAmount() > 0;
    }
}
