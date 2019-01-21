package ch.springboot.fundamentals.pizzaservice.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CatalogRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CatalogRepository catalogRepository;

    @Test
    public void findAllWithOneEntry_entryAvailable() {
        CatalogEntry entry = new CatalogEntry("UnitTestPizza", 1237);
        entityManager.persist(entry);
        entityManager.flush();

        List<CatalogEntry> foundEntries = catalogRepository.findAll();

        Optional<CatalogEntry> foundPizza = foundEntries.stream()
                .filter(pizza -> pizza.getName().equals("UnitTestPizza")).findFirst();

        assertTrue(foundPizza.isPresent());
        assertEquals("UnitTestPizza", foundPizza.get().getName());
        assertEquals(1237, foundPizza.get().getAmount());
    }
}
