package RUcafe;

/**
 * Donut class to extend MenuItem abstract class
 * Represents a Donut item in the menu.
 * It allows specifying donut flavor and type, used to calculate total price.
 *
 * @author Ridwan Sharkar
 */

public class Donut extends MenuItem
{
    // Enumeration for different donut types
    private enum DonutType { YEAST, CAKE, DONUT_HOLE }

    // Enumeration for flavors for each donut type
    public enum YeastDonutFlavor { Strawberry, Glazed, Caramel, Cinnamon, Coconut, Mint }
    public enum CakeDonutFlavor  { Mocha, Chai, Matcha }
    public enum DonutHoleFlavor  { Powdered, Blueberry, Lemon }

    private DonutType donutType;
    private Enum<?> donutFlavor; // Generic enum type for flavor

    // Constants for pricing
    private static final double YEAST_DONUT_PRICE = 1.79;
    private static final double CAKE_DONUT_PRICE = 1.89;
    private static final double DONUT_HOLE_PRICE = 0.39;

    //==================================================================================================================

    /**
     * Constructs a Donut object with specified type and flavor
     *
     * @param donutType the type of the donut
     * @param donutFlavor the flavor of the specified type's donut
     */
    public Donut(DonutType donutType, Enum<?> donutFlavor)
    {
        this.donutType = donutType;
        this.donutFlavor = donutFlavor;
    }

    //==================================================================================================================

    /**
     * Calculates and returns the price of the donut based on only its type.
     *
     * @return the calculated price of the donut
     */
    @Override
    public double price()
    {
        switch (donutType)
        {
            case YEAST:
                return YEAST_DONUT_PRICE;
            case CAKE:
                return CAKE_DONUT_PRICE;
            case DONUT_HOLE:
                return DONUT_HOLE_PRICE;
            default:
                throw new IllegalStateException("Unknown Donut Type: " + donutType);
        }
    }

    //==================================================================================================================

}