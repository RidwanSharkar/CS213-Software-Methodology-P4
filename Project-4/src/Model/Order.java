package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Order class to manage orders in the RU Café system.
 * Each order contains a list of menu items and a unique order number.
 * Order class: Include 2 private instance variables:
 * (1) the order number - a unique number for each order
 * (2) the list of menu items - must use the abstract type MenuItem, which can hold donut, coffee, or sandwich objects.
 *
 * @author Ridwan Sharkar
 */

public class Order {
    private static int nextOrderNumber = 1; // Static counter to generate unique order numbers

    private final int orderNumber;
    private List<MenuItem> items;

    // Singleton instance of the order
    private static Order instance;

    // Private constructor to prevent instantiation from outside
    private Order() {
        this.orderNumber = nextOrderNumber++;
        this.items = new ArrayList<>();
    }

    // Method to get the singleton instance of Order
    public static Order getInstance() {
        if (instance == null) {
            instance = new Order();
        }
        return instance;
    }

    //==================================================================================================================

    /**
     * Adds a menu item to the order.
     *
     * @param item The menu item to be added.
     */
    public void addItem(MenuItem item) {
        items.add(item);
    }

    //==================================================================================================================

    /**
     * Removes a menu item from the order.
     *
     * @param item The menu item to be removed.
     */
    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    //==================================================================================================================

    /**
     * Calculates the total price of all items in the order.
     *
     * @return Total price of the order.
     */
    public double calculateTotal() {
        double total = 0.0;
        for (MenuItem item : items) {
            total += item.price();
        }
        return total;
    }

    //==================================================================================================================

    /**
     * Gets the order number.
     *
     * @return The order number.
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    //==================================================================================================================

    /**
     * Gets the list of items in the order.
     *
     * @return The list of menu items.
     */
    public List<MenuItem> getItems() {
        return new ArrayList<>(items); // Return a copy to preserve encapsulation
    }

    //==================================================================================================================

    // Method to reset the order (for example, when starting a new order)
    public void resetOrder() {
        instance = new Order();
    }

}