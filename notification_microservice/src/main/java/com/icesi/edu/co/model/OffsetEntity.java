package com.icesi.edu.co.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OffsetEntity {
    @Id
    private String topicPartition;
    private long offset;

}
