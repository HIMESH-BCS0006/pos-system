package com.SpringbootAcademy.pos2.service.impl;

import com.SpringbootAcademy.pos2.Entity.Item;
import com.SpringbootAcademy.pos2.Entity.enums.MeasuringUnitType;
import com.SpringbootAcademy.pos2.config.ModelMapperConfig;
import com.SpringbootAcademy.pos2.dto.CustomerDTO;
import com.SpringbootAcademy.pos2.dto.paginated.PaginatedResponseItemDTO;
import com.SpringbootAcademy.pos2.dto.request.ItemRequestDTO;
import com.SpringbootAcademy.pos2.dto.response.ItemGetResponseDTO;
import com.SpringbootAcademy.pos2.exception.NotFoundException;
import com.SpringbootAcademy.pos2.repo.ItemRepo;
import com.SpringbootAcademy.pos2.service.ItemService;
import com.SpringbootAcademy.pos2.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ItemMapper itemMapper;

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
        if (!itemRepo.existsById(item.getItemId())) {
            itemRepo.save(item);
            return itemRequestDTO.getItemName() + "saved successfully";

        } else {
            throw new DuplicateKeyException("Item already exists");

        }


    }

    @Override
    public List<ItemGetResponseDTO> getItemsByNameAndStatus(String itemName) {

        Boolean b = true;


        List<Item> items = itemRepo.findAllByItemNameAndActive(itemName, b);

        if (items.size() > 0) {
            List<ItemGetResponseDTO> itemGetResponseDTO = modelMapper.map(items, new TypeToken<List<ItemGetResponseDTO>>() {
            }.getType());
            return itemGetResponseDTO;


        } else {
            throw new RuntimeException("Item is not active");
        }


    }

    @Override
    public List<ItemGetResponseDTO> getItemsByNameAndStatusByMapstruct(String itemName) {
        Boolean b = true;
        List<Item> items = itemRepo.findAllByItemNameAndActive(itemName, b);

        if (items.size() > 0) {
            List<ItemGetResponseDTO> itemGetResponseDTO = itemMapper.entityListToDTOList(items);
            return itemGetResponseDTO;


        } else {
            throw new RuntimeException("Item is not active");
        }


    }

    @Override
    public List<ItemGetResponseDTO> getItemsByActiveStatus(boolean activeStatus) {


        List<Item> items = itemRepo.findAllByActive(activeStatus);

        if (items.size() > 0) {
            List<ItemGetResponseDTO> itemGetResponseDTO = itemMapper.entityListToDTOList(items);
            return itemGetResponseDTO;


        } else {
            throw new NotFoundException("Item is not active");
        }

    }

    @Override
    public List<ItemGetResponseDTO> getAllItems() {
        List<Item> allItems = itemRepo.findAll();
        if (allItems.size() > 0) {
            List<ItemGetResponseDTO> allItemsDTOList = itemMapper.entityListToDTOList(allItems);

            return allItemsDTOList;

        } else {
            throw new NotFoundException("Item is not active");
        }

    }

    @Override
    public PaginatedResponseItemDTO getAllItemswithPaginated(int page, int size) {

        Page<Item> items = itemRepo.findAll(PageRequest.of(page, size));


        if(items.getTotalElements() > 0){
            PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
                    itemMapper.ListDTOToPage(items),itemRepo.count()
            );
            return paginatedResponseItemDTO;

        }else{
            throw new NotFoundException("Item is not active");
        }


    }


}
