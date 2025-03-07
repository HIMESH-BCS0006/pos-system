package com.SpringbootAcademy.pos2.repo;

import com.SpringbootAcademy.pos2.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Item, Integer> {
}
