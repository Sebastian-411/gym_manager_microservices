package com.icesi.edu.co.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdMember implements Serializable{
    private Long idMember;
}

