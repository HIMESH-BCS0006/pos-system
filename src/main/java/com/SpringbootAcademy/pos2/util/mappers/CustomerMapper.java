package com.SpringbootAcademy.pos2.util.mappers;


import com.SpringbootAcademy.pos2.Entity.Customer;
import com.SpringbootAcademy.pos2.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO entityToDTO(Customer customer);

    Customer dtoTOEntity(CustomerDTO customerDTO);

    List<CustomerDTO> entityListToDTOList(List<Customer> customers);

    List<CustomerDTO> ListDTOTOPage(Page<Customer> customers);
}
