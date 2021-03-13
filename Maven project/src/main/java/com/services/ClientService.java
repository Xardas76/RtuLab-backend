package com.services;

import com.entities.Client;
import com.entities.Item;
import com.entities.Purchase;
import com.exceptions.ClientNotFoundException;
import com.exceptions.LoginIsTaken;
import com.exceptions.WrongDataFormatException;
import com.repositories.ClientRepository;
import com.repositories.PurchaseRepository;
import com.resources.ClientResource;
import com.resources.ItemResource;
import com.resources.PurchaseResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ClientService {
    @Autowired
    private ClientRepository repository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    public ClientService(ClientRepository clientRepository) {
        this.repository = clientRepository;
    }

    public ClientResource getClientResourceByIdOrLogin(String loginOrId) throws ClientNotFoundException {
        Client client = findClient(loginOrId);
        if (client == null) {
            throw new ClientNotFoundException();
        }
        return client.getResource();
    }

    //returns list of purchases (may be empty)
    public List<PurchaseResource> getClientPurchases(String loginOrId) throws ClientNotFoundException {
        Client client = findClient(loginOrId);
        List<PurchaseResource> purchases = new ArrayList<>();
        if (client == null){
            throw new ClientNotFoundException();
        }
        for (Purchase p: client.getPurchases()) {
            purchases.add(p.getResource());
        }
        return purchases;
    }

    public Long add(ClientResource client) throws WrongDataFormatException, LoginIsTaken {
        if (findClient(client.getLogin()) != null) {
            throw new LoginIsTaken();
        }
        if (!validateLogin(client)) {
            throw new WrongDataFormatException();
        }
        if (!validatePassword(client)) {
            throw new WrongDataFormatException();
        }
        Client created = new Client(client);
        created = repository.save(created);
        return created.getId();
    }

    public PurchaseResource makePurchase(String loginOrId, ItemResource itemResource) throws ClientNotFoundException {
        Client client = findClient(loginOrId);
        if (client == null) {
            throw new ClientNotFoundException();
        }
        Item item = new Item(itemResource);
        List<Item> items = new ArrayList<>();
        items.add(item);
        Purchase purchase = new Purchase(items);
        purchase = purchaseRepository.save(purchase);
        return purchase.getResource();
    }

    @Transactional(readOnly = true)
    private Client findClient(String loginOrId){ //MAY RETURN NULL!
        Client found;
        try{
            Long id = Long.parseLong(loginOrId);
            Optional<Client> optionalClient = repository.findById(id);
            if (optionalClient.isEmpty()) {
                found = null;
            }
            else {
                found = optionalClient.get();
            }
        }
        catch (NumberFormatException e) {
            List<Client> listClient = repository.findByLogin(loginOrId);
            if (listClient.isEmpty()) {
                found = null;
            }
            else {
                found = listClient.get(0);
            }
        }
        return found;
    }

    private boolean validateLogin(ClientResource client) {
        String name = client.getName();
        return name.matches("^[a-zA-Z0-9._-]{3,}$")
                && Character.isLetter(name.charAt(0));
    }

    private boolean validatePassword (ClientResource client) {
        String name = client.getName();
        return name.matches("^[a-zA-Z0-9!?.-]{6,}$")
                && Character.isLetter(name.charAt(0));
    }
}
