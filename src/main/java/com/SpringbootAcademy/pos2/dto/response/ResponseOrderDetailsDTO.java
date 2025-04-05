package com.SpringbootAcademy.pos2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseOrderDetailsDTO {
    //customer
    private String customerName;
    private String customerAddress;
    private List<String> contactNumber;

    //order
    private Date date;
    private double total;
}
