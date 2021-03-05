package com.controlers;

import com.resources.ClientResource;
import com.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/client/{id}") //Accepts either login or id.
    @ResponseBody // Returns JSON ClientResource
    public ResponseEntity<ClientResource> getClient(@PathVariable("id") String input){
        ClientResource client;
        try{
            Long id = Long.parseLong(input);
            client = clientService.getClientById(id);
        }
        catch (NumberFormatException e) {
            client = clientService.getClientByLogin(input);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @GetMapping("/client/{userId}/name") //Returns name of client by login/id
    public ResponseEntity<String> getName(@PathVariable("userId") String input){
        ClientResource client;
        try{
            Long id = Long.parseLong(input);
            client = clientService.getClientById(id);
        }
        catch (NumberFormatException e) {
            client = clientService.getClientByLogin(input);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client.getName());
    }

    @PostMapping("/client") //Accepts a client in JSON
    @ResponseBody //Adds the client to DB and returns id
    public ResponseEntity<Long> addClient(@RequestBody ClientResource client) {
        if (clientService.exists(client)) {
            return ResponseEntity.badRequest().build();
        }
        Long id = clientService.add(client);
        if (id == null) {
            return ResponseEntity.notFound().build();
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUri();

        return ResponseEntity.created(uri).body(id);
    }

    @RequestMapping("/") //Default answer
    public String index() {
        return "Welcome message";
    }
}
