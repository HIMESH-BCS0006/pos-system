package com.SpringbootAcademy.pos2.dto.paginated;


import com.SpringbootAcademy.pos2.dto.response.ItemGetResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseItemDTO {

List<ItemGetResponseDTO> list;
private long dataCount;
}
