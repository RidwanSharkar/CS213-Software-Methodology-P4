package Model;
import java.util.ArrayList;
import java.util.List;

/**
 * Sandwich class to extend MenuItem abstract class
 * Represents a Sandwich item in the menu.
 * It allows specifying the combination of Meat, Bread, and Addons to calculate total price
 *
 * @author Ridwan Sharkar
 */

public class Sandwich extends MenuItem
{
    // Enumerations for Meat, Bread, and Addon choices
    private enum MeatChoice  { CHICKEN, FISH, BEEF }
    private enum BreadChoice { WHEAT, BAGEL, SOUR_DOUGH }
    private enum AddOns      { CHEESE, LETTUCE, TOMATOES, ONIONS }

    private final MeatChoice meatChoice;
    private final BreadChoice breadChoice;
    private final List<AddOns> addOnsList; // List of add-ons

    // Constants for Sandwich (Meat) pricing
    private static final double CHICKEN_PRICE = 8.99;
    private static final double FISH_PRICE = 9.99;
    private static final double BEEF_PRICE = 10.99;

    // Constants for Addon pricing
    private static final double CHEESE_PRICE = 1.00;
    private static final double LETTUCE_PRICE = 0.30;
    private static final double TOMATOES_PRICE = 0.30;
    private static final double ONIONS_PRICE = 0.30;

    //==================================================================================================================

    /**
     * Constructs a Sandwich object with specified bread, protein, and add-ons.
     *
     * @param breadChoice the type of bread for the sandwich
     * @param meatChoice the type of protein for the sandwich
     * @param addOnsList list of add-ons for the sandwich
     */
    public Sandwich(MeatChoice meatChoice, BreadChoice breadChoice, List<AddOns> addOnsList)
    {
        this.meatChoice = meatChoice;
        this.breadChoice = breadChoice;
        this.addOnsList = new ArrayList<>(addOnsList);
    }

    //==================================================================================================================

    /**
     * Calculates and returns the price of the sandwich based on protein and add-ons.
     *
     * @return the calculated price of the sandwich
     */
    @Override
    public double price()
    {
        double totalPrice = 0.0;

        // Calculate base price of meat choice
        switch (meatChoice)
        {
            case CHICKEN:
                totalPrice += CHICKEN_PRICE;
                break;
            case FISH:
                totalPrice += FISH_PRICE;
                break;
            case BEEF:
                totalPrice += BEEF_PRICE;
                break;
            default:
                throw new IllegalStateException("Invalid Meat Selection");
        }

        // Add price for addOn
        if (addOnsList != null)
        {
            for (AddOns addOn : addOnsList)
            {
                switch (addOn)
                {
                    case CHEESE:
                        totalPrice += CHEESE_PRICE;
                        break;
                    case LETTUCE:
                        totalPrice += LETTUCE_PRICE;
                        break;
                    case TOMATOES:
                        totalPrice += TOMATOES_PRICE;
                        break;
                    case ONIONS:
                        totalPrice += ONIONS_PRICE;
                        break;
                    default:
                        throw new IllegalStateException("Invalid Add-on Selection");
                }
            }
        }
        return totalPrice;
    }

    //==================================================================================================================

}