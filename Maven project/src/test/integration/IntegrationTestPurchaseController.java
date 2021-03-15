import com.Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resources.ClientResource;
import com.resources.ItemResource;
import com.services.ClientService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTestPurchaseController {
    @Autowired
    MockMvc mvc;

    @Autowired
    ClientService service;

    @Test
    @Order(1)
    public void purchaseItemResource() throws Exception {
        Long id = service.add(new ClientResource("NewYorker56"));

        mvc.perform(put("/purchase/" + id)
                .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(new ItemResource("Doll", 90))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cost", is(90)))
                .andExpect(jsonPath("$.items[0]", is("Doll: 90")));
    }

    @Test
    @Order(2)
    public void purchaseExistingItem() throws Exception {

        mvc.perform(get("/purchase/" + "NewYorker56") //same client, as before
                .param("item", "Doll") //Doll was created in previous test
                .param("cost", "43")) //Cost will be ignored!
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cost", is(90)))
                .andExpect(jsonPath("$.items[0]", is("Doll: 90")));
    }

    @Test
    @Order(3)
    public void getClientPurchases() throws Exception {
        mvc.perform(get("/client/" + "NewYorker56/purchases")) //same client, as before
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].cost", is(90)))
                .andExpect(jsonPath("$[1].items[0]", is("Doll: 90")));
    }

    @Test
    public void purchaseNewItem() throws Exception {
        Long id = service.add(new ClientResource("kider45"));

        mvc.perform(get("/purchase/" + id)
                .param("item", "Toy")
                .param("cost", "60"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cost", is(60)))
                .andExpect(jsonPath("$.items[0]", is("Toy: 60")));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
