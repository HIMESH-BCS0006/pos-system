package com.SpringbootAcademy.pos2.util.mappers;

import com.SpringbootAcademy.pos2.Entity.Item;
import com.SpringbootAcademy.pos2.dto.request.ItemRequestDTO;
import com.SpringbootAcademy.pos2.dto.response.ItemGetResponseDTO;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    List<ItemGetResponseDTO>  entityListToDTOList(List<Item> items);

    List<ItemGetResponseDTO> ListDTOToPage(Page<Item> items);

    //Here if need to map not a list but an object we can use as belows
    //ItemGetResponseDTO entityToDTO(Item item);

    // Thing to map----- methodName(Thing We have );

}
