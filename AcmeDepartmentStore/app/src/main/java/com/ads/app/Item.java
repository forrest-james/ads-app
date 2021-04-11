package com.ads.app;

public class Item {
    // Properties
    private String ItemNumber;
    private String Name;
    private Double Price;
    private Boolean IsPopular;
    private Boolean IsRecommended;

    // Constructors
    public Item() {
        ItemNumber = "00001";
        Name = "TestItem01";
        Price = 1.99;
        IsPopular = false;
        IsRecommended = false;
    }

    public Item(String itemNumber, String name, Double price) {
        ItemNumber = itemNumber;
        Name = name;
        Price = price;
        IsPopular = false;
        IsRecommended = false;
    }

    public Item(String itemNumber, String name, Double price, Boolean isPopular, Boolean isRecommended) {
        ItemNumber = itemNumber;
        Name = name;
        Price = price;
        IsPopular = isPopular;
        IsRecommended = isRecommended;
    }

    // Getters
    public String ItemNumber() {
        return ItemNumber;
    }

    public String Name() {
        return Name;
    }

    public Double Price() {
        return Price;
    }

    public Boolean IsPopular() {
        return IsPopular;
    }

    public Boolean IsRecommended() {
        return IsRecommended;
    }
}