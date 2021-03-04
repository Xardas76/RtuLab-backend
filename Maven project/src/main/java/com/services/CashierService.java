package com.services;

import com.repositories.ClientRepository;
import com.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CashierService {
    @Autowired
    private ClientRepository clients;
    @Autowired
    private PurchaseRepository purchases;

    public void makePurchase(String login, List<String> items) {

    }
/*
    public void makePurchase(Long client_id, List<String> items) {

    }
*/
    public void makePurchase(Long client_id, List<Long> items_id) {

    }
}
