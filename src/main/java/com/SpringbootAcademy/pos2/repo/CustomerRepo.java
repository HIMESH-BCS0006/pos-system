package com.SpringbootAcademy.pos2.repo;

import com.SpringbootAcademy.pos2.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CustomerRepo extends JpaRepository<Customer, Integer> {


    List<Customer> findAllByActive(boolean active);
}
