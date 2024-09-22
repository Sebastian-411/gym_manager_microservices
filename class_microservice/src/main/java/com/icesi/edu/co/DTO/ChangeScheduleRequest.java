package com.icesi.edu.co.DTO;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeScheduleRequest implements Serializable{
    private String classId;
    private LocalDateTime newSchedule;
}
