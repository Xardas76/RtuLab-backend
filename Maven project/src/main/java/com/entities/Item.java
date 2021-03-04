package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Integer cost;

    public Item() {}

    public Item(String name, String description, Integer cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
