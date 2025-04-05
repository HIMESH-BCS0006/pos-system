package com.SpringbootAcademy.pos2.dto.paginated;


import com.SpringbootAcademy.pos2.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedRespondCustomerDTO {


    List<CustomerDTO> list;
    private long dataCount;
}
