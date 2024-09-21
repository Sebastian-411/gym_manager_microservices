package com.icesi.edu.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.icesi.edu.co.model.Class;

public interface ClassRepository extends JpaRepository<Class, Long> {
}
