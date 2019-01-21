package ch.springboot.fundamentals.pizzaservice;

import ch.springboot.fundamentals.pizzaservice.infrastructure.CatalogEntry;
import ch.springboot.fundamentals.pizzaservice.infrastructure.CatalogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzaserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaserviceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CatalogRepository repository) {
        return (args) -> {
            repository.save(new CatalogEntry("Salami", 0));
            repository.save(new CatalogEntry("Funghi", 4));
        };
    }

}

