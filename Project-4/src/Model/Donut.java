package Model;

import java.util.Objects;

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
    public enum DonutType { YEAST, CAKE, DONUT_HOLE }

    // Enumeration for flavors for each donut type
    public enum YeastDonutFlavor { Strawberry, Glazed, Caramel, Cinnamon, Coconut, Mint }
    public enum CakeDonutFlavor  { Mocha, Chai, Matcha }
    public enum DonutHoleFlavor  { Powdered, Blueberry, Lemon }

    private final DonutType donutType;
    private final String donutFlavor; // Generic enum type for flavor

    // Getter methods for donutType and donutFlavor
    public DonutType getDonutType() {
        return donutType;
    }

    public String getDonutFlavor() {
        return donutFlavor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Donut donut = (Donut) obj;
        return donutType == donut.donutType &&
                (Objects.equals(donutFlavor, donut.donutFlavor));
    }

    @Override
    public int hashCode() {
        int result = donutType != null ? donutType.hashCode() : 0;
        result = 31 * result + (donutFlavor != null ? donutFlavor.hashCode() : 0);
        return result;
    }





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
    public Donut(String donutType, String donutFlavor)
    {
        this.donutType = DonutType.valueOf(donutType);
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