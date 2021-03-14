package com.resources;

import java.util.List;

public class PurchaseResource {
    private String date;
    private Integer cost;
    private List<String> items;

    public PurchaseResource(String date, Integer cost, List<String> items) {
        this.date = date;
        this.cost = cost;
        this.items = items;
    }

    public Integer getCost() {
        return cost;
    }

    public List<String> getItems() {
        return items;
    }

    public String getDate() {
        return date;
    }
}
