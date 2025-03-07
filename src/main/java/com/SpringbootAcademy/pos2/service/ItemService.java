package com.SpringbootAcademy.pos2.service;

import com.SpringbootAcademy.pos2.dto.request.ItemRequestDTO;

public interface ItemService {
    String saveItem(ItemRequestDTO itemRequestDTO);
}
