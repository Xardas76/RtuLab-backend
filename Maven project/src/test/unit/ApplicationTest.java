import com.Application;
import com.controlers.ClientController;
import com.controlers.PurchaseController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
@SuppressWarnings("unused")
class ApplicationTest {
    @Autowired
    private ClientController clientController;
    @Autowired
    private PurchaseController purchaseController;

    @Test
    void contextLoads() {
        assertThat(clientController).isNotNull();
        assertThat(purchaseController).isNotNull();
    }
}