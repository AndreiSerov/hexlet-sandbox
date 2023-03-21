package org.example.puzzlers.question;

/**
 * @author andreiserov
 */
public class Coupling {
}

interface ICartItem {
    float total();
}

class CartItem implements ICartItem {
    private float price;
    private int quantity;

    @Override
    public float total() {
        return price * quantity;
    }
}

class Cart {
    public ICartItem[] items;

    public float totalPrice() {
        float total = 0;
        for (ICartItem item : items) {
            total += item.total();
        }
        return total;
    }
}


class OrderCaclucaltion {
}

class Order {
    private Cart cart;
    private float salesTax;

    public Order(Cart cart, float salesTax) {
        this.cart = cart;
        this.salesTax = salesTax;
    }

    public float orderTotal() {
        return cart.totalPrice() * salesTax;
    }
}