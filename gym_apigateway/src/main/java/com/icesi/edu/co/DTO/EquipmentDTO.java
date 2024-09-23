package com.icesi.edu.co.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDTO {

    private Long id;
    private String name;
    private String description;
    private int amount;
}
