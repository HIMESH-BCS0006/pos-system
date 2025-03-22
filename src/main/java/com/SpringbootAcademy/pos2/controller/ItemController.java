package com.SpringbootAcademy.pos2.controller;


import com.SpringbootAcademy.pos2.dto.paginated.PaginatedResponseItemDTO;
import com.SpringbootAcademy.pos2.dto.request.ItemRequestDTO;
import com.SpringbootAcademy.pos2.dto.response.ItemGetResponseDTO;
import com.SpringbootAcademy.pos2.service.ItemService;
import com.SpringbootAcademy.pos2.util.mappers.StandardResponse;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

//    @PostMapping(
//            path = "/save"
//    )
//    public String saveCustomer(@RequestBody ItemRequestDTO itemRequestDTO) {
//        String message = itemService.saveItem(itemRequestDTO);
//        return message;
//    }

    @PostMapping(
            path = "/save"
    )
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemRequestDTO itemRequestDTO) {
        String message = itemService.saveItem(itemRequestDTO);

        //So we need to send a code , message, and the relevent returning thing to put Here
        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
              new StandardResponse(201,"Success",message), HttpStatus.CREATED
        );

        return response;


    }

    @GetMapping(
            path = "/get-all-items",
            params = {"page","size"}

    )
    public ResponseEntity<StandardResponse> getAllItems(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size


            ){
//        List<ItemGetResponseDTO> allItems = itemService.getAllItems(page,size);

        PaginatedResponseItemDTO allItemsDTO = itemService.getAllItemswithPaginated(page,size);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",allItemsDTO), HttpStatus.OK
        );



    }

    @GetMapping(
            path = "/get-by-name",
            params = "name"
    )
    public ResponseEntity<StandardResponse> getItembyStatus(@RequestParam(value ="name") String itemName) {

        List<ItemGetResponseDTO> itemGetResponseDTOS = itemService.getItemsByNameAndStatusByMapstruct(itemName);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",itemGetResponseDTOS), HttpStatus.OK
        );
    }

    @GetMapping(
            path = "/get-by-status",
            params = {"activeStatus","page","size"}
    )
    public ResponseEntity<StandardResponse> getItembyActiveStatus
            (@RequestParam(value ="activeStatus") boolean activeStatus,
             @RequestParam(value = "page") int page,
             @RequestParam (value = "size") @Max(50)int size )  {

        List<ItemGetResponseDTO> itemGetResponseDTOS = itemService.getItemsByActiveStatus(activeStatus);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",itemGetResponseDTOS), HttpStatus.OK
        );
    }





}
