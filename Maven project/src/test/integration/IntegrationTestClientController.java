
import com.Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resources.ClientResource;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//Uses H2 in-memory database (not the production one)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
//@AutoConfigureTestDatabase
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTestClientController {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ClientService service;

    @Test
    public void postTooShortLogin() throws Exception {
        ClientResource client = new ClientResource("bob");

        mvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(client)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postTooShortPassword() throws Exception {
        ClientResource client = new ClientResource("bobRoland643", "123");

        mvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(client)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(1)
    public void postNewClient() throws Exception{
        ClientResource client = new ClientResource("LittlePony123");

        mvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(client)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void getClient() throws Exception {
        mvc.perform(get("/client/LittlePony123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("LittlePony123")));
    }

    @Test
    @Order(1)
    public void getExistingClient() throws Exception {
        Long id = service.add(new ClientResource("NewYorker56"));

        mvc.perform(get("/client/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.password", is("NewYorker56")));
    }


    @Test
    @Order(2)
    public void tryToPostExistingClient() throws Exception{
        ClientResource client = new ClientResource("NewYorker56");

        mvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(client)))
                .andExpect(status().isBadRequest());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
