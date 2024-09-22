package com.icesi.edu.co.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeScheduleDTO implements Serializable {
    private Long classId;
    private String message;
  
}