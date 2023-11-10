package com.example.assignment01.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateDonationForm {
    private  int id;
    private String code;
    private String name;
    private String startDate;
    private String endDate;
    private String organizationName;
    private String phoneNumber;
    private String description;
}
