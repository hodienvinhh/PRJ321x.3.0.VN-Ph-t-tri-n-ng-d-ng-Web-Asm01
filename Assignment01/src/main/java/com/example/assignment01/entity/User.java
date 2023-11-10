package com.example.assignment01.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "`User`")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "EMAIL",nullable = false,unique = true)
    private String email;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "`PASSWORD`", nullable = false)
    private String passWord;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "`STATUS`")
    private int status;

    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String userName;

    @Column(name = "CREATED")
    private String created;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<UserDonation> userDonations;

    public User() {
    }

    public List<UserDonation> getUserDonations() {
        return userDonations;
    }

    public void setUserDonations(List<UserDonation> userDonations) {
        this.userDonations = userDonations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "" +
                "ID :" + id + '\n' +
                "Address :" + address +  '\n' +
                "Email :" + email +  '\n' +
                "FullName :" + fullName +  '\n' +
                "Note :" + note +  '\n' +
                "PassWord :" + passWord +  '\n' +
                "PhoneNumber :" + phoneNumber +  '\n' +
                "Status :" + status +  '\n' +
                "UserName :" + userName +  '\n' +
                "Created :" + created +  '\n' +
                "Role :" + role +  '\n' +
                ' ';
    }
}
