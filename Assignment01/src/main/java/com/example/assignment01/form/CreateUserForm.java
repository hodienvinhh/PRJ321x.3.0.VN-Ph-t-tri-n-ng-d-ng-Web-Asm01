package com.example.assignment01.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserForm {
    private String fullName;
    private String phoneNumber;
    private String userName;
    private String email;
    private String address;
    private String password;
    private int idRole;

}
