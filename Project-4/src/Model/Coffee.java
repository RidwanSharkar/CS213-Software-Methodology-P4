package Model;


/**
 * Coffee class to extend MenuItem abstract class
 * Represents a Coffee item in the menu
 * It allows specifying cup size and number of add-ons to calculate total price
 *
 * @author Ridwan Sharkar
 */

public class Coffee extends MenuItem {

    public enum CupSize {
        SHORT, TALL, GRANDE, VENTI
    }

    private static final double BASE_PRICE = 1.99;
    private static final double SIZE_INCREMENT_PRICE = 0.50; // Assuming each size up costs this much more
    public static final double ADDON_PRICE = 0.30;

    private CupSize cupSize;
    private int addonCount;

    // Constructors and other methods...

    public Coffee(String cupSize, int addonCount) {
        this.cupSize = CupSize.valueOf(cupSize.toUpperCase());
        this.addonCount = addonCount;
    }

    public static double getBasePrice() {
        return BASE_PRICE;
    }

    public static double getSizeIncrementPrice() {
        return SIZE_INCREMENT_PRICE;
    }

    public double price() {
        double price = BASE_PRICE;
        price += this.cupSize.ordinal() * SIZE_INCREMENT_PRICE; // Assuming SHORT is the first enum constant
        price += this.addonCount * ADDON_PRICE;
        return price;
    }

    // Static method to get the price increase for the size
    public static double calculateSizePriceIncrease(String size)
    {
        try
        {
            CupSize cupSize = CupSize.valueOf(size.toUpperCase());
            return cupSize.ordinal() * SIZE_INCREMENT_PRICE;
        }
        catch (IllegalArgumentException e)
        {
            return 0.0;
        }
    }
}