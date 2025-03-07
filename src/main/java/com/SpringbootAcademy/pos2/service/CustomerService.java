package com.SpringbootAcademy.pos2.service;

import com.SpringbootAcademy.pos2.dto.CustomerDTO;
import com.SpringbootAcademy.pos2.dto.request.CustomerUpdateDTO;

import java.util.List;

public interface CustomerService  {

    public String saveCustomer(CustomerDTO customerDTO);

    String update(CustomerUpdateDTO customerUpdateDTO);

    CustomerDTO getCustomerById(int customerId);


    List<CustomerDTO> getAllCustomers();

    String deleteCustomerById(int customerId);

    List<CustomerDTO> getAllCustomersByActiveState(boolean activeState);
}
