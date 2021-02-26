package com.controlers;

import com.entities.Client;
import com.resources.ClientResource;
import com.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;

@RestController
public class FrontController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/client/{id}")
    public String getClient(@PathVariable("id") String input){
        Client client;
        try{
            Long id = Long.parseLong(input);
            client = clientService.getClientById(id);
        }
        catch (NumberFormatException e) {
            client = clientService.getClientByLogin(input);
        }
        if (client == null) {
            return "Nothing was found";
        }
        return "Client's name is " + client.getName();
    }

    @GetMapping("/client/{userId}/name")
    public String getName(@PathVariable("userId") String input){
        try{
            Long id = Long.parseLong(input);
            Client client = clientService.getClientById(id);
            if (client == null) throw new NoSuchElementException();
            return client.getName();
        }
        catch (NoSuchElementException e){
            return "No clients with this id.";
        }
        catch (NumberFormatException e){
            return "Numeric id required";
        }
        catch (Exception e){
            return "Unexpected error.";
        }
    }

    @PostMapping("/client")
    public ResponseEntity<ClientResource> addClient(@RequestBody ClientResource client) {
        if (clientService.exists(client)) {
            return ResponseEntity.notFound().build();
        }
        Long id = clientService.add(client);
        if (id == null) {
            return ResponseEntity.notFound().build();
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping("/")
    public String index() {
        return "Welcome message";
    }
}
