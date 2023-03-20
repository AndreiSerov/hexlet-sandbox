package org.example.puzzlers.question;

/**
 * @author andreiserov
 */
public class Coupling {
}

class Item {
    public float price;
    public int quantity;
}

class Cart {
    public Item[] items;
}

class Order {
    private Cart cart;
    private float salesTax;

    public Order(Cart cart, float salesTax) {
        this.cart = cart;
        this.salesTax = salesTax;
    }

    public float orderTotal() {
        float cartTotal = 0;
        for (int i = 0; i < cart.items.length; i++) {
            cartTotal += cart.items[i].price * cart.items[i].quantity;
        }
        cartTotal += cartTotal * salesTax;
        return cartTotal;
    }
}