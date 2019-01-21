package ch.springboot.fundamentals.pizzaservice.domain;

import ch.springboot.fundamentals.pizzaservice.infrastructure.CatalogEntry;
import ch.springboot.fundamentals.pizzaservice.infrastructure.CatalogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogServiceSpringTest {

    @Autowired
    private CatalogService catalogService;

    @MockBean
    private CatalogRepository mockedCatalogRepository;

    @Test
    public void emptyRepository_notAvailable() {
        when(mockedCatalogRepository.findAll()).thenReturn(new HashSet<>());

        boolean isAvailable = catalogService.isEntryAvailable("testname");

        verify(mockedCatalogRepository, times(1)).findAll();
        assertFalse(isAvailable);
    }

    @Test
    public void unavailableInRepository_notAvailable() {
        HashSet<CatalogEntry> entries = new HashSet<>();
        entries.add(new CatalogEntry("testname", 0));
        when(mockedCatalogRepository.findAll()).thenReturn(entries);

        boolean isAvailable = catalogService.isEntryAvailable("testname");

        verify(mockedCatalogRepository, times(1)).findAll();
        assertFalse(isAvailable);
    }

    @Test
    public void availableInRepository_available() {
        HashSet<CatalogEntry> entries = new HashSet<>();
        entries.add(new CatalogEntry("testname", 5));
        when(mockedCatalogRepository.findAll()).thenReturn(entries);

        boolean isAvailable = catalogService.isEntryAvailable("testname");

        verify(mockedCatalogRepository, times(1)).findAll();
        assertTrue(isAvailable);
    }


}