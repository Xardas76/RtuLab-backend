package com.controlers;

import com.resources.ClientResource;
import com.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

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
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
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
            return ResponseEntity.badRequest().build();
        }
        if (client == null) {
            return new ResponseEntity<>("Nothing was found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client.getName(), HttpStatus.OK);
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
