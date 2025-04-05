package com.SpringbootAcademy.pos2.dto.request;


import com.SpringbootAcademy.pos2.Entity.Customer;
import com.SpringbootAcademy.pos2.Entity.OrderDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDTO {
    private int customer;
    private Date date;
    private double total;
    private List<RequestOrderDetailSaveDTO> orderDetails;
}
