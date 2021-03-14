package com.controlers;

import com.exceptions.ClientNotFoundException;
import com.exceptions.LoginIsTaken;
import com.exceptions.WrongDataFormatException;
import com.resources.ClientResource;
import com.resources.PurchaseResource;
import com.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/client/{id}") //Accepts either login or id.
    @ResponseBody // Returns JSON ClientResource
    public ResponseEntity<ClientResource> getClientJSON(@PathVariable("id") String input){
        ClientResource client;
        try {
            client = clientService.getClientResourceByIdOrLogin(input); //null if there is no client
        }
        catch (ClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            System.out.println("---> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(client);
    }

    @GetMapping("/client/{id}/name") //Returns name of client by login/id
    public ResponseEntity<String> getClientName(@PathVariable("id") String input){
        ClientResource client;
        try {
            client = clientService.getClientResourceByIdOrLogin(input); //null if there is no client
        }
        catch (ClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            System.out.println("---> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(client.getName());
    }


    @GetMapping("/client/{id}/purchases") //Accepts either login or id of client.
    @ResponseBody // Returns List JSON
    public ResponseEntity<List<PurchaseResource>> getClientPurchaseStory(@PathVariable("id") String input){
        List<PurchaseResource> responseBody;
        try {
            responseBody = clientService.getClientPurchases(input);
        } catch (ClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            System.out.println("---> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/client") //Accepts a client in JSON
    // Adds the client to DB and returns id
    public ResponseEntity<Long> addClient(@RequestBody ClientResource client) {
        Long id;
        try {
            id = clientService.add(client);
        }
        catch (WrongDataFormatException e) {
            return ResponseEntity.badRequest().build();
        } catch (LoginIsTaken e){
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            System.out.println("---> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        if (id == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUri();

        return ResponseEntity.created(uri).body(id);
    }
}
