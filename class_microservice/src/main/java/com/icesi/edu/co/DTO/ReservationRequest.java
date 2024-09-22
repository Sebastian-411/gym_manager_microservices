package com.icesi.edu.co.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest implements Serializable {
    private Long classId;
    private Long memberId;
}
