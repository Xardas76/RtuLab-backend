package com.entities;

import com.resources.ItemResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@SuppressWarnings("unused")
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

    public  Item(ItemResource resource) {
        id = null;
        name = resource.getName();
        description = resource.getDescription();
        cost = resource.getCost();
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

    @Override
    public String toString() {
        return name + ": " + cost;
    }
}
