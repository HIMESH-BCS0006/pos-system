package com.SpringbootAcademy.pos2.repo;


import com.SpringbootAcademy.pos2.Entity.Order;
import com.SpringbootAcademy.pos2.Entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderDetailRepo extends JpaRepository<OrderDetails, Integer> {


}
