package com.example.assignment01.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class CreateDonationForm {
    private String code;
    private String name;
    private String startDate;
    private String endDate;
    private String organizationName;
    private String phoneNumber;
    private String description;
}
