package com.SpringbootAcademy.pos2.dto.paginated;


import com.SpringbootAcademy.pos2.dto.response.ResponseOrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseOrderDetailsDTO {

    private List<ResponseOrderDetailsDTO> list;
    private long dataCount;
}
