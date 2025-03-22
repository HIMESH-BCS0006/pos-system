package com.SpringbootAcademy.pos2.service.impl;

import com.SpringbootAcademy.pos2.Entity.Customer;
import com.SpringbootAcademy.pos2.dto.CustomerDTO;
import com.SpringbootAcademy.pos2.dto.paginated.PaginatedRespondCustomerDTO;
import com.SpringbootAcademy.pos2.dto.paginated.PaginatedResponseItemDTO;
import com.SpringbootAcademy.pos2.dto.request.CustomerUpdateDTO;
import com.SpringbootAcademy.pos2.exception.NotFoundException;
import com.SpringbootAcademy.pos2.repo.CustomerRepo;
import com.SpringbootAcademy.pos2.service.CustomerService;
import com.SpringbootAcademy.pos2.util.mappers.CustomerMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        //   System.out.println(customerDTO.getContactNumber());
//        Customer customer = new Customer(customerDTO.getId(),
//                customerDTO.getCustomerName(),
//                customerDTO.getCustomerAddress(),
//                customerDTO.getCustomerSalary(),
//                (ArrayList) customerDTO.getContactNumber(),
//                customerDTO.getNic(),
//                customerDTO.isActive()
//        );

        Customer customer = customerMapper.dtoTOEntity(customerDTO);
        if(!customerRepo.existsById(customer.getId())){
                customerRepo.save(customer);
                return customerDTO.getCustomerName() + "saved successfully";
        }else{
            throw  new DuplicateKeyException("Customer already exists");
        }



//        customerRepo.save(customer);

//        return customerDTO.getCustomerName() + "saved successfully";
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
//            CustomerDTO customerDTO = new CustomerDTO(
//                    customer.getId(),
//                    customer.getCustomerName(),
//                    customer.getCustomerAddress(),
//                    customer.getCustomerSalary(),
//                    (ArrayList) customer.getContactNumber(),
//                    customer.getNic(),
//                    customer.isActive()
//            );
            CustomerDTO customerDTO = customerMapper.entityToDTO(customer);

            return customerDTO;

        } else {
            throw new NotFoundException("Customer Not Found");
        }

    }

    @Override
    public List<CustomerDTO> getAllCustomers() {


        List<Customer> allCustomers = customerRepo.findAll();
        if(!allCustomers.isEmpty()) {
            List<CustomerDTO> allCustomerDTOList = customerMapper.entityListToDTOList(allCustomers);

//        for( Customer customer : allCustomers){
//           CustomerDTO customerDTO = new CustomerDTO(
//                   customer.getId(),
//                   customer.getCustomerName(),
//                   customer.getCustomerAddress(),
//                   customer.getCustomerSalary(),
//                   (ArrayList) customer.getContactNumber(),
//                   customer.getNic(),
//                   customer.isActive()
//           );
//
//           allCustomerDTOList.add(customerDTO);
//        }


            return allCustomerDTOList;
        }else{
            throw new NotFoundException("No Customers Found!!");
        }
    }

    @Override
    public String deleteCustomerById(int customerId) {
        if (customerRepo.existsById(customerId)) {
            customerRepo.deleteById(customerId);

            return customerId + "deleted successfully";


        }else{
           throw new NotFoundException("Customer Not Found");
        }


    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState(boolean activeState) {

        List<Customer> allCustomers = customerRepo.findAllByActive(activeState);
        if(!allCustomers.isEmpty()) {
            List<CustomerDTO> allCustomerDTOList = customerMapper.entityListToDTOList(allCustomers);

//        for( Customer customer : allCustomers){
//            CustomerDTO customerDTO = new CustomerDTO(
//                    customer.getId(),
//                    customer.getCustomerName(),
//                    customer.getCustomerAddress(),
//                    customer.getCustomerSalary(),
//                    (ArrayList) customer.getContactNumber(),
//                    customer.getNic(),
//                    customer.isActive()
//            );
//
//            allCustomerDTOList.add(customerDTO);
//        }


            return allCustomerDTOList;
        }else{
            throw new NotFoundException("No Records Found!!");
        }
    }

    @Override
    public PaginatedRespondCustomerDTO getAllCustomersWithPagination(int page, int size) {
        Page<Customer> customers = customerRepo.findAll(PageRequest.of(page, size));

        if(customers.getTotalElements()>0){

            PaginatedRespondCustomerDTO paginatedRespondCustomerDTO = new PaginatedRespondCustomerDTO(
                    customerMapper.ListDTOTOPage(customers), customerRepo.count()
            );

            return paginatedRespondCustomerDTO;
        }else{
            throw new NotFoundException("No Records Found!!");
        }




    }

    @Override
    public PaginatedRespondCustomerDTO getAllCustomersByActiveStateAndPagination(boolean activeState, int page, int size) {

        Page<Customer> customers = customerRepo.findAllByActive(activeState,PageRequest.of(page,size));

        if(customers.getTotalElements()>0){

            PaginatedRespondCustomerDTO paginatedRespondCustomerDTO = new PaginatedRespondCustomerDTO(
                    customerMapper.ListDTOTOPage(customers), customerRepo.count()
            );

            return paginatedRespondCustomerDTO;

        }else{
            throw new NotFoundException("No Records Found!!");
        }

    }

}
