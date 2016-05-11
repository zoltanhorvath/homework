package hu.horvathzoltan.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    @NotNull
    @Size(min = 1)
    private String zipCode;

    @Basic
    @NotNull
    @Size(min = 1)
    private String country;

    @Basic
    @NotNull
    @Size(min = 1)
    private String city;

    @Basic
    @NotNull
    @Size(min = 1)
    private String street;

    @Basic
    @NotNull
    @Size(min = 1)
    private String streetNumber;

    public Address() {
        //No-arg constructor
    }

    public Address(String zipCode, String country, String city, String street, String streetNumber) {
        this.zipCode = zipCode;
        this.country = country;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return this.streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
}
