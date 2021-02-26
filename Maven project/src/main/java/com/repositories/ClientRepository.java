package com.repositories;

import com.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByLogin(String login);

    List<Client> findByLoginAndNameAndPasswordAndBalance(String login, String name, String password, Integer balance);
}
