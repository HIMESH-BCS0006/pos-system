package com.SpringbootAcademy.pos2.repo;

import com.SpringbootAcademy.pos2.Entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Item, Integer> {
//    List<Item> findAllByItemNameEqual(String itemName, boolean b);

    List<Item> findAllByItemNameAndActive(String itemName, boolean b);

    List<Item> findAllByActive(boolean activeStatus);

    Page<Item> findAll(Pageable pageable);


}
