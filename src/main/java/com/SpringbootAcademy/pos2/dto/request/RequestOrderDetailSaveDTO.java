package com.SpringbootAcademy.pos2.dto.request;


import com.SpringbootAcademy.pos2.Entity.Item;
import com.SpringbootAcademy.pos2.Entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailSaveDTO {

    private String itemName;
    private double qty;
    private double amount;
    private int items;
   // private int orders;
}
