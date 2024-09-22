package com.icesi.edu.co.model;

import com.icesi.edu.co.DTO.ReservationDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeTraining implements Serializable {
    private int totalBooking;

    public ResumeTraining updateBooking(ReservationDTO reservation) {
        this.totalBooking += 1;
        return this;
    }

}
