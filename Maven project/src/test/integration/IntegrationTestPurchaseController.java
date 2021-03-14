import com.Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resources.ClientResource;
import com.resources.ItemResource;
import com.services.ClientService;
import org.junit.jupiter.api.Test;
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
public class IntegrationTestPurchaseController {
    @Autowired
    MockMvc mvc;

    @Autowired
    ClientService service;

    @Test
    public void purchase() throws Exception {
        Long id = service.add(new ClientResource("NewYorker56"));

        mvc.perform(post("/purchase/" + id)
                .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(new ItemResource("Doll", 90))))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cost", is(90)));
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
