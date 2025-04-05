package com.SpringbootAcademy.pos2.service;

import com.SpringbootAcademy.pos2.dto.paginated.PaginatedResponseOrderDetailsDTO;
import com.SpringbootAcademy.pos2.dto.request.RequestOrderSaveDTO;

public interface OrderService {
    String saveOrder(RequestOrderSaveDTO requestOrderSaveDTO);

    PaginatedResponseOrderDetailsDTO getOrderDetails(boolean status, int page, int size);
}
