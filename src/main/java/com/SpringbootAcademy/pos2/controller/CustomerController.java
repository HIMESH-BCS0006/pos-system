package com.SpringbootAcademy.pos2.controller;


import com.SpringbootAcademy.pos2.Entity.Customer;
import com.SpringbootAcademy.pos2.dto.CustomerDTO;
import com.SpringbootAcademy.pos2.dto.paginated.PaginatedRespondCustomerDTO;
import com.SpringbootAcademy.pos2.dto.paginated.PaginatedResponseItemDTO;
import com.SpringbootAcademy.pos2.dto.request.CustomerUpdateDTO;
import com.SpringbootAcademy.pos2.exception.NotFoundException;
import com.SpringbootAcademy.pos2.service.CustomerService;
import com.SpringbootAcademy.pos2.util.mappers.StandardResponse;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

//    @PostMapping("/save-customer")
//    public String saveCustomer(@RequestBody CustomerDTO customerDTO) {
//       /* String str = customerDTO.getCustomerName();
//        System.out.println("come "+ str); */
//        String message = customerService.saveCustomer(customerDTO);
//
//        return message;
//    }

    @PostMapping("/save-customer")
    public ResponseEntity<StandardResponse> saveCustomer(@RequestBody CustomerDTO customerDTO) {
       /* String str = customerDTO.getCustomerName();
        System.out.println("come "+ str); */
        String message = customerService.saveCustomer(customerDTO);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message), HttpStatus.CREATED
        );

        return response;


    }

//    @PutMapping("/update-customer")
//    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
//        String message = customerService.update(customerUpdateDTO);
//
//        return message;
//    }

    @PutMapping("/update-customer")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        String message = customerService.update(customerUpdateDTO);
        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",message), HttpStatus.OK
        );


        return response;
    }


//    @GetMapping(
//            path = "/get-by-id",
//            params = "id"
//    )
//    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId) {
//
//        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
//
//        return customerDTO;
//    }

    @GetMapping(
            path = "/get-by-id",
            params = "id"
    )
    public ResponseEntity<StandardResponse> getCustomerById(@RequestParam(value = "id") int customerId) {

        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Success", customerDTO), HttpStatus.OK
            );

          return response;
    }


//    @GetMapping(
//            path = "/get-all-customers"
//    )
//    public List<CustomerDTO> getAllCustomers(){
//        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
//
//        return customerDTOList;
//    }

    @GetMapping(
            path = "/get-all-customers",
            params = {"page","size"}

    )
    public ResponseEntity<StandardResponse> getAllCustomers(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size")  int size
    ){
        //List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
        PaginatedRespondCustomerDTO customerList = customerService.getAllCustomersWithPagination(page,size);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",customerList), HttpStatus.OK
        );

        return response;
    }



//    @DeleteMapping(
//            path = "delete-customer/{id}"
//    )
//    public String deleteCustomer(@PathVariable(value = "id") int customerId) {
//        String message= customerService.deleteCustomerById(customerId);
//
//        return message;
//    }

    @DeleteMapping(
            path = "delete-customer/{id}"
    )
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable(value = "id") int customerId) {
        String message= customerService.deleteCustomerById(customerId);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",message), HttpStatus.OK
        );
    }



//    @GetMapping(
//            path = "/get-all-customers-by-active-state/{state}"
//    )
//    public List<CustomerDTO> getAllCustomersByActiveState(@PathVariable(value = "state") boolean activeState){
//
//        List<CustomerDTO> customerDTOList = customerService.getAllCustomersByActiveState(activeState);
//
//        return customerDTOList;
//
//    }

    @GetMapping(
            path = "/get-all-customers-by-active-state/{state}"

    )
    public ResponseEntity<StandardResponse>  getAllCustomersByActiveState(@PathVariable(value = "state") boolean activeState){

        List<CustomerDTO> customerDTOList = customerService.getAllCustomersByActiveState(activeState);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",customerDTOList), HttpStatus.OK
        );

    }

    @GetMapping(
            path = "/get-all-customers-by-active-state",
            params = {"state","page","size"}
    )
    public ResponseEntity<StandardResponse>  getAllCustomersByActiveStateAndPagination(@RequestParam(value = "state") boolean activeState,
                                                                                       @RequestParam(value = "page" )int page,
                                                                                       @RequestParam(value = "size") int size){

        //List<CustomerDTO> customerDTOList = customerService.getAllCustomersByActiveState(activeState);
        PaginatedRespondCustomerDTO customerDTOList = customerService.getAllCustomersByActiveStateAndPagination(activeState,page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",customerDTOList), HttpStatus.OK
        );

    }



}

