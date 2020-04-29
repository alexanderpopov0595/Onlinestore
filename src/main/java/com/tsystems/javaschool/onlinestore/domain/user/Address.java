package com.tsystems.javaschool.onlinestore.domain.user;

import com.tsystems.javaschool.onlinestore.enums.Status;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

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

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

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

    public void setStatus(String status){
        this.status=Status.valueOf(status);
    }
    public Status getStatus(){
        return status;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}