package ch.springboot.fundamentals.pizzaservice.api;

import ch.springboot.fundamentals.pizzaservice.domain.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/catalog")
public class CatalogRestController {

    private final CatalogService catalogService;

    @Autowired
    public CatalogRestController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping(path = "/{name}")
    public CatalogEntryDto isPizzaAvailable(@PathVariable String name) {
        boolean isAvailable = catalogService.isEntryAvailable(name);
        return new CatalogEntryDto(name, isAvailable);
    }

    @GetMapping
    public Set<CatalogEntryDto> getAll() {
        return catalogService.getAll().stream()
                .map(CatalogEntryDto::from)
                .collect(Collectors.toSet());
    }
}
