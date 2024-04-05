package Model;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * A class to test the price() method in Sandwich class
 * 5 different test cases each with a unique sandwich
 * @author Ryan Colling
 */
public class SandwichTest {

    /**
     * Tests if a chicken sandwich with no add-ons is 8.99 with price() method
     */
    @Test
    public void testSandwich1() {
        List<Sandwich.AddOns> list = new ArrayList<>();
        Sandwich sand = new Sandwich(Sandwich.MeatChoice.CHICKEN, Sandwich.BreadChoice.WHEAT, list);
        Double tprice = 8.99;
        Double cprice = sand.price();
        assertEquals("A Chicken sandwich no add ons is 8.99", tprice, cprice);
    }

    /**
     * Tests if a fish sandwich with tomatoes  is 10.29 with price() method
     */
    @Test
    public void testSandwich2() {
        List<Sandwich.AddOns> list = new ArrayList<>();
        list.add(Sandwich.AddOns.TOMATOES);
        Sandwich sand = new Sandwich(Sandwich.MeatChoice.FISH, Sandwich.BreadChoice.BAGEL, list);
        Double tprice = 9.99 + 0.30;
        Double cprice = sand.price();
        assertEquals("A Fish sandwich with lettuce is 10.29", tprice, cprice);
    }

    /**
     * Tests if a beef sandwich with cheese is 11.99 with price() method
     */
    @Test
    public void testSandwich3() {
        List<Sandwich.AddOns> list = new ArrayList<>();
        list.add(Sandwich.AddOns.CHEESE);
        Sandwich sand = new Sandwich(Sandwich.MeatChoice.BEEF, Sandwich.BreadChoice.SOUR_DOUGH, list);
        Double tprice = 10.99 + 1.00;
        Double cprice = sand.price();
        assertEquals("A beef sandwich with cheese is 11.99", tprice, cprice);
    }

    /**
     * Tests if a chicken sandwich with every vegetable add on is 9.89 with price() method
     */
    @Test
    public void testSandwich4() {
        List<Sandwich.AddOns> list = new ArrayList<>();
        list.add(Sandwich.AddOns.ONIONS);
        list.add(Sandwich.AddOns.TOMATOES);
        list.add(Sandwich.AddOns.LETTUCE);
        Sandwich sand = new Sandwich(Sandwich.MeatChoice.CHICKEN, Sandwich.BreadChoice.BAGEL, list);
        Double tprice = 8.99 + (0.30 * 3);
        Double cprice = sand.price();
        Double roundOff = (double) Math.round(cprice * 100) / 100; //need this to round calculations to the hundreths place
        assertEquals("A chicken sandwich with onions, tomatoes, and lettuce is 9.89", tprice, roundOff);
    }

    /**
     * Tests if a beef sandwich with every add on is 12.89 with price() method
     */
    @Test
    public void testSandwich5() {
        List<Sandwich.AddOns> list = new ArrayList<>();
        list.add(Sandwich.AddOns.ONIONS);
        list.add(Sandwich.AddOns.TOMATOES);
        list.add(Sandwich.AddOns.LETTUCE);
        list.add(Sandwich.AddOns.CHEESE);
        Sandwich sand = new Sandwich(Sandwich.MeatChoice.BEEF, Sandwich.BreadChoice.WHEAT, list);
        Double tprice = 10.99 + 1.00 + (0.30 * 3);
        Double cprice = sand.price();
        Double roundOff = (double) Math.round(cprice * 100) / 100; //need this to round calculations to the hundreths place
        assertEquals("A beef sandwich with onions, tomatoes, lettuce, and cheese is 12.89", tprice, roundOff);
    }
}