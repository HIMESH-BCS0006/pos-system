package com.SpringbootAcademy.pos2.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")

public class Order {


    @Id
    @Column(name = "order_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int orderId;

    //...
    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    @Column(name = "order_date", columnDefinition = "DATETIME")
    private Date date;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "active_state" , columnDefinition = "TINYINT default 0")
    private boolean active;

    @OneToMany(mappedBy="orders")
    private Set<OrderDetails> orderDetails;

    public Order(Customer customer, Date date, double total) {
        this.customer = customer;
        this.date = date;
        this.total = total;
    }
}
