package ch.springboot.fundamentals.pizzaservice.domain;

import ch.springboot.fundamentals.pizzaservice.infrastructure.CatalogEntry;
import ch.springboot.fundamentals.pizzaservice.infrastructure.CatalogRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CatalogServiceTest {

    private CatalogRepository mockedCatalogRepository;
    private CatalogService catalogService;

    @Before
    public void setUp() {
        mockedCatalogRepository = mock(CatalogRepository.class);
        catalogService = new CatalogService(mockedCatalogRepository);
    }

    @Test
    public void emptyRepository_notAvailable() {
        when(mockedCatalogRepository.findAll()).thenReturn(new ArrayList<>());

        boolean isAvailable = catalogService.isEntryAvailable("testname");

        verify(mockedCatalogRepository, times(1)).findAll();
        assertFalse(isAvailable);
    }

    @Test
    public void unavailableInRepository_notAvailable() {
        List<CatalogEntry> entries = new ArrayList<>();
        entries.add(new CatalogEntry("testname", 0));
        when(mockedCatalogRepository.findAll()).thenReturn(entries);

        boolean isAvailable = catalogService.isEntryAvailable("testname");

        verify(mockedCatalogRepository, times(1)).findAll();
        assertFalse(isAvailable);
    }

    @Test
    public void availableInRepository_available() {
        List<CatalogEntry> entries = new ArrayList<>();
        entries.add(new CatalogEntry("testname", 5));
        when(mockedCatalogRepository.findAll()).thenReturn(entries);

        boolean isAvailable = catalogService.isEntryAvailable("testname");

        verify(mockedCatalogRepository, times(1)).findAll();
        assertTrue(isAvailable);
    }
}