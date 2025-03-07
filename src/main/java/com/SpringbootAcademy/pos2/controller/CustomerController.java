package com.SpringbootAcademy.pos2.controller;


import com.SpringbootAcademy.pos2.Entity.Customer;
import com.SpringbootAcademy.pos2.dto.CustomerDTO;
import com.SpringbootAcademy.pos2.dto.request.CustomerUpdateDTO;
import com.SpringbootAcademy.pos2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save-customer")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO) {
       /* String str = customerDTO.getCustomerName();
        System.out.println("come "+ str); */
        String message = customerService.saveCustomer(customerDTO);

        return message;
    }

    @PutMapping("/update-customer")
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        String message = customerService.update(customerUpdateDTO);

        return message;
    }


    @GetMapping(
            path = "/get-by-id",
            params = "id"
    )
    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId) {

        CustomerDTO customerDTO = customerService.getCustomerById(customerId);

        return customerDTO;
    }


    @GetMapping(
            path = "/get-all-customers"
    )
    public List<CustomerDTO> getAllCustomers(){

        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();

        return customerDTOList;



    }

    @DeleteMapping(
            path = "delete-customer/{id}"
    )
    public String deleteCustomer(@PathVariable(value = "id") int customerId) {
        String message= customerService.deleteCustomerById(customerId);

        return message;
    }

    @GetMapping(
            path = "/get-all-customers-by-active-state/{state}"
    )
    public List<CustomerDTO> getAllCustomersByActiveState(@PathVariable(value = "state") boolean activeState){

        List<CustomerDTO> customerDTOList = customerService.getAllCustomersByActiveState(activeState);

        return customerDTOList;



    }



}

