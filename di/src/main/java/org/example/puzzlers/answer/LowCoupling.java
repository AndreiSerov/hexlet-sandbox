package org.example.puzzlers.answer;

/**
 * @author andreiserov
 */
// todo make interface to improve cohesion
public class LowCoupling {
}


class Item {
    public float price;
    public int quantity;

    public float getTotalPrice() {
        return price * quantity;
    }
}

class Cart {
    public Item[] items;

    public float getCartItemsTotal() {
        float cartTotal = 0;
        for (Item item : items) {
            cartTotal += item.getTotalPrice();
        }
        return cartTotal;
    }
}

class Order {
    private Cart cart;
    private float salesTax;

    public Order(Cart cart, float salesTax) {
        this.cart = cart;
        this.salesTax = salesTax;
    }

    public float orderTotal() {
        return cart.getCartItemsTotal() * (1.0f + salesTax);
    }
}