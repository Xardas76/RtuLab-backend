import com.Application;
import com.entities.Item;
import com.entities.Purchase;
import com.resources.PurchaseResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JsonTest
@ContextConfiguration(classes= Application.class)
public class TestPurchaseResource {
    @Autowired
    private JacksonTester<PurchaseResource> json;

    @Test
    public void toResource() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("pen", "blue pen", 100));
        items.add(new Item("eraser", "big white eraser", 40));
        Purchase purchase = new Purchase(items);
        PurchaseResource resource = purchase.getResource();

        assertEquals(resource.getCost(), 140);
        assertEquals(resource.getItems().get(0), "pen: 100");
        assertEquals(resource.getItems().get(1), "eraser: 40");
    }

    @Test
    public void resourceToJson() throws IOException {
        List<String> items = new ArrayList<>();
        items.add("milk");
        items.add("bread");
        PurchaseResource resource = new PurchaseResource("14-03-21", 345, items);
        JsonContent<PurchaseResource> result = json.write(resource);

        assertThat(result).extractingJsonPathStringValue("$.date").isEqualTo("14-03-21");
        assertThat(result).extractingJsonPathNumberValue("$.cost").isEqualTo(345);
        assertThat(result).extractingJsonPathStringValue("$.items[0]").isEqualTo("milk");

    }

    @Test
    //FULL Item -> Purchase -> PurchaseResource -> Json test
    public void fullCycleTest() throws IOException {
        Item item = new Item("Monitor", "LG monitor", 15300);
        List<Item> list = new ArrayList<>();
        list.add(item);
        Purchase purchase = new Purchase(list, "03-15-21");
        PurchaseResource resource = purchase.getResource();
        JsonContent<PurchaseResource> result = json.write(resource);

        assertThat(result).extractingJsonPathStringValue("$.date").isEqualTo("03-15-21");
        assertThat(result).extractingJsonPathNumberValue("$.cost").isEqualTo(15300);
        assertThat(result).extractingJsonPathStringValue("$.items[0]").isEqualTo("Monitor: 15300");
    }
}
