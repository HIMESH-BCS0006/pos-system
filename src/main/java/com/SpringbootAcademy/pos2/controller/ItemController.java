package com.SpringbootAcademy.pos2.controller;


import com.SpringbootAcademy.pos2.dto.request.ItemRequestDTO;
import com.SpringbootAcademy.pos2.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(
            path = "/save"
    )
    public String saveCustomer(@RequestBody ItemRequestDTO itemRequestDTO) {


        String message = itemService.saveItem(itemRequestDTO);

        return message;
    }





}
