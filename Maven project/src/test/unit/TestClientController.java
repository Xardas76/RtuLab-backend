import com.Application;
import com.controlers.ClientController;
import com.resources.ClientResource;
import com.services.ClientService;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ClientController.class)
@ContextConfiguration(classes= Application.class)
public class TestClientController {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private ClientService clientService;

    @Test
    public void findExistingClient() throws Exception{
        when(clientService.getClientResourceByIdOrLogin("236")).thenReturn(new ClientResource("jacksparrow"));

        mockMvc.perform(get("/client/{userId}", 236))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login", is("jacksparrow")));
    }
}
