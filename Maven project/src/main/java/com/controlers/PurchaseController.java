package com.controlers;

import com.exceptions.ClientNotFoundException;
import com.exceptions.ItemNotFoundException;
import com.resources.ItemResource;
import com.resources.PurchaseResource;
import com.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class PurchaseController {
    ClientService clientService;

    public PurchaseController(ClientService clientService) {
        this.clientService = clientService;
    }

    /*
    //Cart feature to be developed later
    @PostMapping("/checkout/{id}")
    public ResponseEntity<String> checkoutClient() {
        return null;
    }
*/
    @PutMapping("/purchase/{id}") //Accepts JSON Item {name, description, cost}
    public ResponseEntity<PurchaseResource> purchaseItemResource(@PathVariable String id, @RequestBody ItemResource item) {
        PurchaseResource purchase;
        try {
            purchase = clientService.purchaseItemResource(id, item);
        } catch (ClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            System.out.println("---> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(purchase);
    }

    @GetMapping("/purchase/{id}") //Accepts Client Id and Item in params
    public ResponseEntity<PurchaseResource> purchaseItem(@PathVariable String id,
                                                         @RequestParam String item,
                                                         @RequestParam(required = false) Integer cost,
                                                         @RequestParam(required = false) String description) {
        PurchaseResource purchase;
        try {
            if (cost == null) {
                purchase = clientService.findAndPurchaseItem(id, item);
            }
            else if (description == null) {
                ItemResource itemResource = new ItemResource(item, cost);
                purchase = clientService.purchaseItemResource(id, itemResource);
            }
            else {
                ItemResource itemResource = new ItemResource(item, description, cost);
                purchase = clientService.purchaseItemResource(id, itemResource);
            }

        } catch (ClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ItemNotFoundException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            System.out.println("---> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).body(purchase);
    }
}
