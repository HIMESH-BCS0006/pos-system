package com.SpringbootAcademy.pos2.service.impl;

import com.SpringbootAcademy.pos2.Entity.Item;
import com.SpringbootAcademy.pos2.Entity.enums.MeasuringUnitType;
import com.SpringbootAcademy.pos2.config.ModelMapperConfig;
import com.SpringbootAcademy.pos2.dto.request.ItemRequestDTO;
import com.SpringbootAcademy.pos2.repo.ItemRepo;
import com.SpringbootAcademy.pos2.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public String saveItem(ItemRequestDTO itemRequestDTO) {
//        Item item = new Item(
//                1,
//                itemRequestDTO.getItemName(),
//                itemRequestDTO.getMeasuringUnitType(),
//                itemRequestDTO.getBalanceQty(),
//                itemRequestDTO.getSupplierPrice(),
//                itemRequestDTO.getSellingPrice(),
//                true
//        );

        //itemRepo.save(item);

        Item item = modelMapper.map(itemRequestDTO, Item.class);
       if(!itemRepo.existsById(item.getItemId())){
           return itemRequestDTO.getItemName() + "saved successfully";

       }else {
           throw  new DuplicateKeyException("Item already exists");

       }


    }

}
