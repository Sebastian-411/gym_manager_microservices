package com.icesi.edu.co.repository;

import com.icesi.edu.co.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long>  {
    
}
