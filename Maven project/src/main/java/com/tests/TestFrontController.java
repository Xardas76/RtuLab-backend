package com.tests;

import com.controlers.FrontController;
import com.services.ClientService;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FrontController.class)
public class TestFrontController {
    @MockBean
    private ClientService clientService;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void findExistingClient() throws Exception{
        mockMvc.perform(get("/client/{userId}", 236))
                .andExpect(status().isOk())
                .andExpect(content().string("Jack Sparrow"));
    }
}
