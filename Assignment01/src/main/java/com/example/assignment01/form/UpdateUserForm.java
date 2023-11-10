package com.example.assignment01.form;

import com.example.assignment01.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserForm {

    private int id;
    private String address;
    private String fullName;
    private String phoneNumber;
    private int idRole;
}
