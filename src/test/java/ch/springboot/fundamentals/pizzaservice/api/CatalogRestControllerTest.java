package ch.springboot.fundamentals.pizzaservice.api;

import ch.springboot.fundamentals.pizzaservice.domain.CatalogService;
import ch.springboot.fundamentals.pizzaservice.infrastructure.CatalogEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CatalogRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatalogService catalogService;

    @Test
    public void getAll() throws Exception {
        CatalogEntry margherita = new CatalogEntry("Margherita", 100);
        CatalogEntry salami = new CatalogEntry("Salami", 0);
        List<CatalogEntry> allEmployees = Arrays.asList(margherita, salami);
        when(catalogService.getAll()).thenReturn(allEmployees);

        mockMvc.perform(get("/api/catalog")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Margherita"))
                .andExpect(jsonPath("$[0].available").value(true))
                .andExpect(jsonPath("$[1].name").value("Salami"))
                .andExpect(jsonPath("$[1].available").value(false));
    }

    @Test
    public void isAvailable() throws Exception {
        when(catalogService.isEntryAvailable("Margherita")).thenReturn(true);

        mockMvc.perform(get("/api/catalog/{name}", "Margherita")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Margherita"))
                .andExpect(jsonPath("$.available").value(true));
    }
}