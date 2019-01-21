package ch.springboot.fundamentals.pizzaservice.domain;

import ch.springboot.fundamentals.pizzaservice.infrastructure.CatalogEntry;
import ch.springboot.fundamentals.pizzaservice.infrastructure.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class CatalogService {

    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public boolean isEntryAvailable(String name) {
        return catalogRepository.findAll().stream()
                .filter(isInStock())
                .anyMatch(e -> e.getName().equals(name));
    }

    public List<CatalogEntry> getAll() {
        return catalogRepository.findAll();
    }

    private Predicate<CatalogEntry> isInStock() {
        return entry -> entry.getAmount() > 0;
    }
}
