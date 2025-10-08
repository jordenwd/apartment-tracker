package org.jwd.apartmenttracker;

import org.jwd.apartmenttracker.entities.Apartment;
import org.jwd.apartmenttracker.entities.Floorplan;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApartmentTests {

    @Test
    public void testApartmentGettersAndSetters() {
        Apartment apartment = new Apartment();
        apartment.setName("Test Apartment");
        apartment.setStreetAddress("123 Test St");
        apartment.setCity("Test City");
        apartment.setState("TS");
        apartment.setZipCode(12345);
        apartment.setUrl("http://test.com");

        assertEquals("Test Apartment", apartment.getName());
        assertEquals("123 Test St", apartment.getStreetAddress());
        assertEquals("Test City", apartment.getCity());
        assertEquals("TS", apartment.getState());
        assertEquals(12345, apartment.getZipCode());
        assertEquals("http://test.com", apartment.getUrl());
    }

    @Test
    public void testApartmentFloorplans() {
        Apartment apartment = new Apartment();
        List<Floorplan> floorplans = new ArrayList<>();
        Floorplan floorplan1 = new Floorplan();
        floorplan1.setName("1 Bed 1 Bath");
        Floorplan floorplan2 = new Floorplan();
        floorplan2.setName("2 Bed 2 Bath");
        floorplans.add(floorplan1);
        floorplans.add(floorplan2);

        apartment.setFloorplans(floorplans);

        assertEquals(2, apartment.getFloorplans().size());
        assertEquals("1 Bed 1 Bath", apartment.getFloorplans().get(0).getName());
        assertEquals("2 Bed 2 Bath", apartment.getFloorplans().get(1).getName());
    }

    @Test
    public void testApartmentConstructor() {
        Apartment apartment = new Apartment("Test Apartment", "123 Test St", "Test City", "TS", 12345, "http://test.com");

        assertEquals("Test Apartment", apartment.getName());
        assertEquals("123 Test St", apartment.getStreetAddress());
        assertEquals("Test City", apartment.getCity());
        assertEquals("TS", apartment.getState());
        assertEquals(12345, apartment.getZipCode());
        assertEquals("http://test.com", apartment.getUrl());
    }
}
