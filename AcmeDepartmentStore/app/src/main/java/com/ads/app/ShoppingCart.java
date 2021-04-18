package com.ads.app;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    // Singleton
    private static ShoppingCart _instance = null;

    private ShoppingCart() { }

    public static ShoppingCart getInstance() {
        if(_instance == null) {
            _instance = new ShoppingCart();
        }
        return _instance;
    }

    // Properties
    List<ShoppingCartItem> CartItems = new ArrayList<>();

    // Getters
    public List<ShoppingCartItem> CartItems() {
        return CartItems;
    }

    public Double CartTotal() {
        Double tempTotal = 0.0;
        for(int i = 0; i < CartItems.size(); i++) {
            tempTotal += CartItems.get(i).TotalPrice();
        }
        return tempTotal;
    }

    // Public Methods
    public void addItem(Item item, int quantity) {
        CartItems.add(new ShoppingCartItem(item, quantity));
    }

    public void incrementItem(ShoppingCartItem item) {
        CartItems.get(CartItems.indexOf(item)).incrementItem();
    }

    public void decrementItem(ShoppingCartItem item) {
        if(item.Quantity() > 1) {
            CartItems.get(CartItems.indexOf(item)).decrementItem();
        }
        else {
            // If quantity is 1 (or less), decrementing = remove
            removeItem(item);
        }
    }

    public void removeItem(ShoppingCartItem item) {
        CartItems.remove(item);
    }
}
