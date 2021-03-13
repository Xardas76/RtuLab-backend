package com.resources;

import java.util.List;

public class PurchaseResource {
    private Long id;
    private String date;
    private Integer cost;
    private List<String> items;

    public PurchaseResource(Long id, String date, Integer cost, List<String> items) {
        this.id = id;
        this.date = date;
        this.cost = cost;
        this.items = items;
    }
}
