package ch.springboot.fundamentals.pizzaservice.infrastructure;

import javax.persistence.*;

import static java.util.Objects.requireNonNull;

@Entity
public class CatalogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int amount;

    private CatalogEntry() {
    }

    public CatalogEntry(String name, int amount) {
        this.name = requireNonNull(name);
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
