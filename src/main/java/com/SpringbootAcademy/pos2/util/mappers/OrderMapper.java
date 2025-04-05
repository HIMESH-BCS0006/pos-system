package com.SpringbootAcademy.pos2.util.mappers;


import com.SpringbootAcademy.pos2.Entity.Item;
import com.SpringbootAcademy.pos2.Entity.OrderDetails;
import com.SpringbootAcademy.pos2.dto.request.RequestOrderDetailSaveDTO;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderMapper {

   Set<OrderDetails> DTOToOrderDetailsSet(Set<RequestOrderDetailSaveDTO> orderDetails);

   default Item map(int itemId) {
      Item item = new Item();
      item.setId(itemId);
      return item;
   }

}
