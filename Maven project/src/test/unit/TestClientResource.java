package unit;

import com.entities.Client;
import com.resources.ClientResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class TestClientResource {
    @Test
    public void toResource() {
        Client client = new Client(null, "admin", "Jonna Jackson", "qwerty", 512);
        ClientResource resource = client.getResource();
        assertEquals(resource.getName(), "Jonna Jackson");
        assertEquals(resource.getLogin(), "admin");
        assertEquals(resource.getPassword(), "qwerty");
        assertEquals(resource.getBalance(), 512);
    }
/*
    @Test
    public void toClient() {
        ClientResource resource = new ClientResource("admin", "Jonna Jackson", "qwerty", 512);
        Client client = resource.getClient();
        assertEquals(client.getName(), "Jonna Jackson");
        assertEquals(client.getLogin(), "admin");
        assertEquals(client.getPassword(), "qwerty");
        assertEquals(client.getBalance(), 512);
    }*/
}
