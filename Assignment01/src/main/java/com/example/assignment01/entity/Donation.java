package com.example.assignment01.entity;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "DONATION")
public class Donation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "`CODE`", length = 255,nullable = false,unique = true)
    private String code;

    @Column(name = "CREATED")
//    @Temporal(TemporalType.TIMESTAMP)
//    @CreationTimestamp
    private String created;

    @Column(name = "`DESCRIPTION`", length = 255)
    private String description;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
//    @CreationTimestamp
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
//    @CreationTimestamp
    private Date endDate;

    @Column(name = "MONEY" , nullable = false)
    private int money;

    @Column(name = "`NAME`", length = 255)
    private String name;

    @Column(name = "ORGANIZATION_NAME", length = 255)
    private String organizationName;

    @Column(name = "PHONE_NUMBER", length = 50,nullable = false,unique = true)
    private String phoneNumber;

    @Column(name = "`STATUS`")
    private int status;

    @OneToMany(mappedBy = "donation")
    private List<UserDonation> userDonations;

    public Donation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    public List<UserDonation> getUserDonations() {
        return userDonations;
    }

    public void setUserDonations(List<UserDonation> userDonations) {
        this.userDonations = userDonations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return " " +
                "ID :" + id +'\n' +
                "Code :" + code + '\n' +
                "Created :" + created + '\n' +
                "Description :" + description + '\n' +
                "StartDate :" + startDate + '\n' +
                "EndDate :" + endDate + '\n' +
                "Money :" + money + '\n' +
                "Name :" + name + '\n' +
                "OrganizationName :" + organizationName + '\n' +
                "PhoneNumber :" + phoneNumber + '\n' +
                "Status :" + status + '\n' +
                ' ';
    }
}
