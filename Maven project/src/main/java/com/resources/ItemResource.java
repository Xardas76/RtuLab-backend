package com.resources;

@SuppressWarnings("unused")
public class ItemResource {
    private String name;
    private String description;
    private Integer cost;

    public ItemResource(String name, String description, Integer cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public ItemResource(String name, Integer cost) {
        this.name = name;
        this.cost = cost;
        this.description = "Simple " + name + ".";
    }

    public ItemResource() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCost() {
        return cost;
    }
}
