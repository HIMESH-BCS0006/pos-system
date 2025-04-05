package com.SpringbootAcademy.pos2.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_details")

public class OrderDetails {

    @Id
    @Column(name = "order_details_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int orderDetailsId;

    @Column(name = "item_name",length = 100,nullable = false)
    private String itemName;

    @Column(name = "qty",length = 100,nullable = false)
    private double qty;

    @Column(name = "amount",length = 100,nullable = false)
    private double amount;

    //...
    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private Item items;

    //...
    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order orders;

}
