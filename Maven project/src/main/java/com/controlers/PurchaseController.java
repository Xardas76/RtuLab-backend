package com.controlers;

import com.exceptions.ClientNotFoundException;
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

    @PostMapping("/checkout/{id}")
    public ResponseEntity<String> checkoutClient() {
        return null;
    }

    @PostMapping("/purchase/{id}") //Accepts JSON Item {name, description, cost}
    public ResponseEntity<PurchaseResource> purchaseItem(@PathVariable String id, @RequestBody ItemResource item) {
        PurchaseResource purchase;
        try {
            purchase = clientService.makePurchase(id, item);
        } catch (ClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).body(purchase);
    }
}
