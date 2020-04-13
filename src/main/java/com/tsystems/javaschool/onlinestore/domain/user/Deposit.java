package com.tsystems.javaschool.onlinestore.domain.user;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="deposits")
public class Deposit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="number", unique=true, length=19)
    @Size(min=13, max=19)
    private String number;

    @Column(name="password")
    @Size(min=4, max=4)
    private String password;

    @Column(name="balance")
    private long balance;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", unique=true)
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(long balance) {
        this.balance=balance;
    }
    public long getBalance() {
        return balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}