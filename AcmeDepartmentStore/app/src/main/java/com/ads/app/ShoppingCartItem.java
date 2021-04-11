package com.ads.app;

public class ShoppingCartItem {
    // Properties
    private Item Item;
    private int Quantity;

    // Constructor
    public ShoppingCartItem(Item item, int quantity) {
        Item = item;
        Quantity = quantity;
    }

    // Getters
    public Item Item() {
        return Item;
    }

    public int Quantity() {
        return Quantity;
    }

    public double TotalPrice() {
        return Item.Price() * Quantity;
    }

    // Public methods
    public void incrementItem() {
        Quantity += 1;
    }

    public void decrementItem() {
        Quantity -= 1;
    }
}