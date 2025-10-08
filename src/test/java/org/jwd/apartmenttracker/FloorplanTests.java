package org.jwd.apartmenttracker;

import org.jwd.apartmenttracker.entities.Floorplan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloorplanTests {

    @Test
    public void testFloorplanGettersAndSetters() {
        Floorplan floorplan = new Floorplan();
        floorplan.setName("Test Floorplan");
        floorplan.setBed("1");
        floorplan.setBath("1");
        floorplan.setSquareFeet("700");
        floorplan.setPriceRange("$1000");

        assertEquals("Test Floorplan", floorplan.getName());
        assertEquals("1", floorplan.getBed());
        assertEquals("1", floorplan.getBath());
        assertEquals("700", floorplan.getSquareFeet());
        assertEquals("$1000", floorplan.getPriceRange());
    }

    @Test
    public void testGetMinPrice() {
        Floorplan floorplan = new Floorplan();

        floorplan.setPriceRange("$1,200 - $1,500");
        assertEquals(1200, floorplan.getMinPrice());

        floorplan.setPriceRange("$1,300");
        assertEquals(1300, floorplan.getMinPrice());

        floorplan.setPriceRange("Call for pricing");
        assertEquals(0, floorplan.getMinPrice());

        floorplan.setPriceRange(null);
        assertEquals(0, floorplan.getMinPrice());

        floorplan.setPriceRange("$1,800, $1,900, $1,700");
        assertEquals(1700, floorplan.getMinPrice());
    }

    @Test
    public void testGetMaxPrice() {
        Floorplan floorplan = new Floorplan();

        floorplan.setPriceRange("$1,200 - $1,500");
        assertEquals(1500, floorplan.getMaxPrice());

        floorplan.setPriceRange("$1,300");
        assertEquals(1300, floorplan.getMaxPrice());

        floorplan.setPriceRange("Call for pricing");
        assertEquals(0, floorplan.getMaxPrice());

        floorplan.setPriceRange(null);
        assertEquals(0, floorplan.getMaxPrice());

        floorplan.setPriceRange("$1,800, $1,900, $1,700");
        assertEquals(1900, floorplan.getMaxPrice());
    }

    @Test
    public void testGetMaxPriceBugAndFix() {
        Floorplan floorplan = new Floorplan();
        floorplan.setPriceRange("$1,800 - $1,900");
        assertEquals(1900, floorplan.getMaxPrice());
    }
}
