package test;

import com.Application;
import com.controlers.ClientController;
import com.resources.ClientResource;
import com.services.ClientService;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

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
        when(clientService.getClientById(236L)).thenReturn(new ClientResource("jacksparrow"));

        mockMvc.perform(get("/client/{userId}", 236))
                .andExpect(status().isOk())
                .andExpect(content().json(contains("jacksparrow")));
    }
}
