package com.example.assignment01.entity;

import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "USER_DONATION")
public class UserDonation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CREATED", length = 255, nullable = false)
    private String created;

    @Column(name = "MONEY")
    private int money;

    @Column(name = "`NAME`", length = 255, nullable = false)
    private String name;

    @Column(name = "DATE_DONATION")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateDonation;

    @Column(name = "`STATUS`")
    private int status;

    @Column(name = "`TEXT`", length = 255)
    private String text;

    @ManyToOne
    @JoinColumn(name = "DONATION_ID")
    private Donation donation;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public UserDonation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateDonation() {
        return dateDonation;
    }

    public void setDateDonation(Date dateDonation) {
        this.dateDonation = dateDonation;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "" +
                "ID :" + id +'\n' +
                "Created :" + created + '\n' +
                "Money :" + money + '\n' +
                "Name :" + name + '\n' +
                "DateDonation :" + dateDonation + '\n' +
                "Status :" + status + '\n' +
                "Text :" + text + '\n' +
                "Donation :" + donation + '\n' +
                "User :" + user + '\n' +
                ' ';
    }
}
