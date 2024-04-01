package RUcafe;

/**
 * Coffee class to extend MenuItem abstract class
 * Represents a Coffee item in the menu
 * It allows specifying cup size and number of add-ons to calculate total price
 *
 * @author Ridwan Sharkar
 */

public class Coffee extends MenuItem
{
    // Enumeration for different cup sizes
    private enum CupSize { SHORT, TALL, GRANDE, VENTI }

    // Constants for pricing
    private static final double BASE_PRICE = 1.99;
    private static final double SIZE_INCREMENT_PRICE = 0.50;
    private static final double ADDON_PRICE = 0.30;

    private final CupSize cupSize;
    private final int addonCount; // Number of addons

    //==================================================================================================================

    /**
     * Constructs a Coffee object with specified cup size and number of add-ons.
     *
     * @param cupSize the size of the coffee cup
     * @param addonCount the number of add-ons for the coffee
     */
    public Coffee(CupSize cupSize, int addonCount)
    {
        this.cupSize = cupSize;
        this.addonCount = addonCount;
    }

    //==================================================================================================================

    /**
     * Calculates and returns the price of the coffee based on its size and add-ons.
     *
     * @return the calculated price of the coffee
     */
    @Override
    public double price()
    {
        return BASE_PRICE + (cupSize.ordinal() * SIZE_INCREMENT_PRICE) + (addonCount * ADDON_PRICE);
        // ordinal based on enum order - DO NOT CHANGE
    }

    //==================================================================================================================

}