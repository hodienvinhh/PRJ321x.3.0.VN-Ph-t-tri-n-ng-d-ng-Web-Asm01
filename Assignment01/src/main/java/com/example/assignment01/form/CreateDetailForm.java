package com.example.assignment01.form;

import com.example.assignment01.entity.Donation;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateDetailForm {
    private String name;
    private int money;
    private String text;
    private int idDonation;

}
