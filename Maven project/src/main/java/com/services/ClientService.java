package com.services;

import com.entities.Client;
import com.repositories.ClientRepository;
import com.resources.ClientResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public ClientService(ClientRepository clientRepository) {
        this.repository = clientRepository;
    }

    @Transactional(readOnly = true)
    public Client getClientByLogin(String login) {
        List<Client> found = repository.findByLogin(login);
        if (found.isEmpty()){
            return null;
        }
        else {
            return found.get(0);
        }
    }

    @Transactional(readOnly = true)
    public Client getClientById(Long id) {
        Optional<Client> res = repository.findById(id);
        if (res.isEmpty()){
            return null;
        }
        else {
            return res.get();
        }
    }

    public Long add(ClientResource client) {
        Client created = new Client(client.getLogin(),
                client.getName(), client.getPassword(), client.getBalance());
        created = repository.save(created);
        return created.getId();
    }

    @Transactional(readOnly = true)
    public boolean exists(ClientResource client) {
        List<Client> found = repository.findByLoginAndNameAndPasswordAndBalance(client.getLogin(),
                client.getName(), client.getPassword(), client.getBalance());
        if (found.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }
}