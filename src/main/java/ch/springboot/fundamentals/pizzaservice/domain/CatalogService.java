package ch.springboot.fundamentals.pizzaservice.domain;

import ch.springboot.fundamentals.pizzaservice.infrastructure.CatalogEntry;
import ch.springboot.fundamentals.pizzaservice.infrastructure.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Service
public class CatalogService implements HealthIndicator, InfoContributor {

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

    @Override
	public Health health() {
        if(catalogRepository.findAll().isEmpty()) {
            return Health.outOfService().withDetail("errorDetail", "Error occured").build();
        }
        return Health.up().build();
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("someInfo",
                Collections.singletonMap("someKey", "someValue"));
    }

    private Predicate<CatalogEntry> isInStock() {
        return entry -> entry.getAmount() > 0;
    }
}
