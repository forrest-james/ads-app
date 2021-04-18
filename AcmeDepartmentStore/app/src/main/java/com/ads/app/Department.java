package com.ads.app;

import java.util.ArrayList;
import java.util.List;

public class Department {

    // Properties
    private String Name;
    private List<Item> Items;

    // Constructors
    public Department() {
        Name = "TestDepartment";
        Items = new ArrayList<>();
    }

    public Department(String name) {
        Name = name;
        Items = new ArrayList<>();
    }

    public Department(String name, List<Item> items) {
        Name = name;
        Items = items;
    }

    // Getters
    public String Name() {
        return Name;
    }

    public List<Item> Items() {
        return Items;
    }

    // Public Methods
    public void addItem(Item item) {
        Items.add(item);
    }

    public List<Item> getPopularItems() {
        List<Item> tempList = new ArrayList<>();
        for(int i = 0; i < Items.size(); i++) {
            if(Items.get(i).IsPopular()) {
                tempList.add(Items.get(i));
            }
        }
        return tempList;
    }

    public List<Item> getRecommendedItems() {
        List<Item> tempList = new ArrayList<>();
        for(int i = 0; i < Items.size(); i++) {
            if(Items.get(i).IsRecommended()) {
                tempList.add(Items.get(i));
            }
        }
        return tempList;
    }

    public int findItem(Item item) {
        return Items.indexOf(item);
    }
}