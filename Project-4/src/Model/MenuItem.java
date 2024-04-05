package Model;

/**
 * Abstract class representing a general item on the menu in RU Café.
 * This class is to be extended by specific menu item classes such as Coffee, Donut, and Sandwich.
 * Each subclass should provide its own implementation of the price calculation.
 *
 * MenuItem class: This is an abstract class and the superclass of all menu items.
 * Any class defined for a menu item must extend this class, or -5 points for each violation.
 * You must include the abstract method: public abstract double price(); //subclasses must implement this method
 *
 * @author Ridwan Sharkar
 */

public abstract class MenuItem
{
    /**
     * Calculates and returns the price of the menu item.
     * This method must be implemented by each subclass.
     *
     * @return the price of the menu item
     */
    public abstract double price();                                         // Don't implement in this class




    /*====================================================================================================================*/




    /*------------------------------------------------------

    public MenuItem(){add type}

        public Object getType() {
    }

    public String getDetails(){
        return "";
    }

    public MenuItem(Double price) { this.price = price; }

    public void setPrice(Double p){
        this.price = p;
    }

    public Double getPrice(){
        return price;
    }

    public int getQuantity() {
        return 0;
    }

    public String getFlavor() {
        return null;
    }

    public String getType() {
        return null;
    }

    ------------------------------------------------------*/

}