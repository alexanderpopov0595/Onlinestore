package com.tsystems.javaschool.onlinestore.domain.user;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.tsystems.javaschool.onlinestore.enums.Status;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname")
    @Pattern(regexp = "[a-zA-Z, ]+$")
    private String firstName;

    @Column(name = "lastname")
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z, ]+$")
    private String lastName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "login", unique = true, length = 32)
    @Size(min = 3, max = 32)
    @Pattern(regexp = "[a-zA-Z0-9]+$")
    private String login;

    @Column(name = "password")
    @Size(min = 5, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9]+$")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Address> addressList;

    public User() {

        this.addressList = new ArrayList<>();
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {

        return birthday;
    }

    public void setBirthday(String birthday) {

        this.birthday = LocalDate.parse(birthday).plusDays(1);
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public void setRole(String role) {

        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAddressList(List<Address> addressList) {

        this.addressList = addressList;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddresses() {
        for(Address a: this.addressList){
            a.setUser(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
