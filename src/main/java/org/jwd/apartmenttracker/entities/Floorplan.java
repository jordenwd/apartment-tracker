package org.jwd.apartmenttracker.entities;

import jakarta.persistence.*;

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


    public int getMinPrice() {
        if (priceRange == null || priceRange.isEmpty()) {
            return 0;
        }
        priceRange = priceRange.replace("$", "").replace(",", "").trim();
        if (priceRange.contains("-")) {
            String[] parts = priceRange.split("-");
            try {
                return Integer.parseInt(parts[0].trim());
            } catch (NumberFormatException e) {
                return 0;
            }
        } else {
            try {
                return Integer.parseInt(priceRange.trim());
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    public int getMaxPrice() {
        if (priceRange == null || priceRange.isEmpty()) {
            return 0;
        }
        priceRange = priceRange.replace("$", "").replace(",", "").trim();
        if (priceRange.contains("-")) {
            String[] parts = priceRange.split("-");
            try {
                return Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                return 0;
            }
        } else {
            try {
                return Integer.parseInt(priceRange.trim());
            } catch (NumberFormatException e) {
                return 0;
            }
        }
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
