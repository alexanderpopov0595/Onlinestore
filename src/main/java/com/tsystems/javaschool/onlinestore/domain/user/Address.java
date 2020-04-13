package com.tsystems.javaschool.onlinestore.domain.user;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "zipcode")
    private long zipcode;

    @Column(name = "street")
    private String street;

    @Column(name = "building")
    private int building;

    @Column(name = "apartment")
    private int apartment;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setUser(User user) {

        this.user = user;
    }

    public User getUser() {

        return user;
    }

    public String getCountry() {

        return country;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public long getZipcode() {

        return zipcode;
    }

    public void setZipcode(long zipcode) {

        this.zipcode = zipcode;
    }

    public String getStreet() {

        return street;
    }

    public void setStreet(String street) {

        this.street = street;
    }

    public int getBuilding() {

        return building;
    }

    public void setBuilding(int building) {

        this.building = building;
    }

    public int getApartment() {

        return apartment;
    }

    public void setApartment(int apartment) {

        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", zipCode=" + zipcode +
                ", street='" + street + '\'' +
                ", building=" + building +
                ", apartment=" + apartment +
                '}';
    }
}