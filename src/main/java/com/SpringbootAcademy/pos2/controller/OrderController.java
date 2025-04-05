package com.SpringbootAcademy.pos2.controller;


import com.SpringbootAcademy.pos2.dto.paginated.PaginatedResponseOrderDetailsDTO;
import com.SpringbootAcademy.pos2.dto.request.RequestOrderSaveDTO;
import com.SpringbootAcademy.pos2.service.OrderService;
import com.SpringbootAcademy.pos2.util.mappers.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping(
            path = "/save-order"

    )
    public ResponseEntity<StandardResponse> saveOrder(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO) {
        String message = orderService.saveOrder(requestOrderSaveDTO);

        return new ResponseEntity<>(
                new StandardResponse(200,"Success",message ), HttpStatus.CREATED
        );

    }


    @GetMapping(
            path = {"/get-order-details"},
            params = {"stateType","page","size"}
    )
    public ResponseEntity<StandardResponse> getOrderDetails(
            @RequestParam(value = "stateType") String stateType,
            @RequestParam(value = "page" )int page,
            @RequestParam(value = "size") int size

    ){
        PaginatedResponseOrderDetailsDTO p = null;
        if(stateType.equalsIgnoreCase("active") | stateType.equalsIgnoreCase("inactive")){
            boolean status = stateType.equalsIgnoreCase("active")?true:false;
            p = orderService.getOrderDetails(status,page,size);
        }

        return new ResponseEntity<>(
                new StandardResponse(200,"Success",p ), HttpStatus.OK
        );

    }
}
