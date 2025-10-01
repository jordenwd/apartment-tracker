package org.jwd.apartmenttracker.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name="apartments")
public class Apartment {

   @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

   @Column(nullable=false)
    private String name;
   @Column(nullable=false)
    private String streetAddress;
   @Column(nullable=false)
    private String city;



       @Column
    private String state;
   @Column(nullable=false)
    private int zipCode;

   @OneToMany(mappedBy = "apartment")
    private List<Floorplan> floorplans;

    public Apartment() {

    }

    public Apartment(String name, String streetAddress, String city, String state, int zipCode, List<Floorplan> floorplans) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipCode = zipCode;
        this.floorplans = floorplans;
        this.state = state;
    }


    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public List<Floorplan> getFloorplans() {
        return floorplans;
    }

    public void setFloorplans(List<Floorplan> floorplans) {
        this.floorplans = floorplans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return getZipCode() == apartment.getZipCode() && Objects.equals(getName(), apartment.getName()) && Objects.equals(getStreetAddress(), apartment.getStreetAddress()) && Objects.equals(getCity(), apartment.getCity()) && Objects.equals(getFloorplans(), apartment.getFloorplans());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStreetAddress(), getCity(), getZipCode(), getFloorplans());
    }
}
