package com.SpringbootAcademy.pos2.service.impl;

import com.SpringbootAcademy.pos2.Entity.Customer;
import com.SpringbootAcademy.pos2.dto.CustomerDTO;
import com.SpringbootAcademy.pos2.dto.request.CustomerUpdateDTO;
import com.SpringbootAcademy.pos2.repo.CustomerRepo;
import com.SpringbootAcademy.pos2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        //   System.out.println(customerDTO.getContactNumber());
        Customer customer = new Customer(customerDTO.getId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerSalary(),
                (ArrayList) customerDTO.getContactNumber(),
                customerDTO.getNic(),
                customerDTO.isActive()
        );

        customerRepo.save(customer);

        return customerDTO.getCustomerName() + "saved successfully";
    }

    @Override
    public String update(CustomerUpdateDTO customerUpdateDTO) {
        if (customerRepo.existsById(customerUpdateDTO.getId())) {

            //Get all the information about the customer that is already stored in the datbase
            Customer customer = customerRepo.getReferenceById(customerUpdateDTO.getId());

            customer.setCustomerName(customerUpdateDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());
            customer.setCustomerSalary(customerUpdateDTO.getCustomerSalary());


            // should again save to update the dsatabase
            customerRepo.save(customer);

            return customerUpdateDTO.getCustomerName() + " updated Successfully";
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        if (customerRepo.existsById(customerId)) {
            Customer customer = customerRepo.getReferenceById(customerId);
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    (ArrayList) customer.getContactNumber(),
                    customer.getNic(),
                    customer.isActive()
            );

            return customerDTO;

        } else {
            throw new RuntimeException("Customer not found");
        }

    }

    @Override
    public List<CustomerDTO> getAllCustomers() {


        List<Customer> allCustomers = customerRepo.findAll();
        List<CustomerDTO> allCustomerDTOList = new ArrayList<>();

        for( Customer customer : allCustomers){
           CustomerDTO customerDTO = new CustomerDTO(
                   customer.getId(),
                   customer.getCustomerName(),
                   customer.getCustomerAddress(),
                   customer.getCustomerSalary(),
                   (ArrayList) customer.getContactNumber(),
                   customer.getNic(),
                   customer.isActive()
           );

           allCustomerDTOList.add(customerDTO);
        }


        return allCustomerDTOList;
    }

    @Override
    public String deleteCustomerById(int customerId) {
        if (customerRepo.existsById(customerId)) {
            customerRepo.deleteById(customerId);

            return customerId + "deleted successfully";


        }else{
           throw new RuntimeException("Customer not found on that id");
        }


    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState(boolean activeState) {

        List<Customer> allCustomers = customerRepo.findAllByActive(activeState);
        List<CustomerDTO> allCustomerDTOList = new ArrayList<>();

        for( Customer customer : allCustomers){
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    (ArrayList) customer.getContactNumber(),
                    customer.getNic(),
                    customer.isActive()
            );

            allCustomerDTOList.add(customerDTO);
        }


        return allCustomerDTOList;
    }

}
