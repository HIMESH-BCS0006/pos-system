package com.SpringbootAcademy.pos2.service;

import com.SpringbootAcademy.pos2.dto.paginated.PaginatedResponseItemDTO;
import com.SpringbootAcademy.pos2.dto.request.ItemRequestDTO;
import com.SpringbootAcademy.pos2.dto.response.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    String saveItem(ItemRequestDTO itemRequestDTO);

    List<ItemGetResponseDTO> getItemsByNameAndStatus(String itemName);

    List<ItemGetResponseDTO> getItemsByNameAndStatusByMapstruct(String itemName);


    List<ItemGetResponseDTO> getItemsByActiveStatus(boolean activeStatus);

    List<ItemGetResponseDTO> getAllItems();

    PaginatedResponseItemDTO getAllItemswithPaginated(int page, int size);
}