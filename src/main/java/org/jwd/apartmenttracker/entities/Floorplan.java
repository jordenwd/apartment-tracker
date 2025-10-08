package org.jwd.apartmenttracker.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class Floorplan {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String name;
    @Column
    private String bed;
    @Column
    private String bath;
    @Column
    private String squareFeet;
    @Column
    private String priceRange;



   @ManyToOne
    private Apartment apartment;


    /**
     * Get the minimum price from the priceRange string.
     * @return the minimum price, or 0 if no valid price is found.
     */
    public int getMinPrice() {
        if (priceRange == null || priceRange.isEmpty()) {
            return 0;
        }
        var priceRangeTemp = priceRange.replace("$", "").replace(",", "");
        String[] numbers = priceRangeTemp.split("[^0-9]+");
        int minPrice = Integer.MAX_VALUE;
        for (String number : numbers) {
            if (!number.isEmpty()) {
                try {
                    int price = Integer.parseInt(number);
                    if (price < minPrice) {
                        minPrice = price;
                    }
                } catch (NumberFormatException e) {
                    // ignore if number is not valid
                }
            }
        }
        return minPrice == Integer.MAX_VALUE ? 0 : minPrice;
    }

    /**
     * Get the maximum price from the priceRange string.
     * @return the maximum price, or 0 if no valid price is found.
     */
    public int getMaxPrice() {
        if (priceRange == null || priceRange.isEmpty()) {
            return 0;
        }
        var priceRangeTemp = priceRange.replace("$", "").replace(",", "");
        String[] numbers = priceRangeTemp.split("[^0-9]+");
        int maxPrice = 0;
        for (String number : numbers) {
            if (!number.isEmpty()) {
                try {
                    int price = Integer.parseInt(number);
                    if (price > maxPrice) {
                        maxPrice = price;
                    }
                } catch (NumberFormatException e) {
                    // ignore if number is not valid
                }
            }
        }
        return maxPrice;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }

    public String getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(String squareFeet) {
        this.squareFeet = squareFeet;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

}
