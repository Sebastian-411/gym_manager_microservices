package com.icesi.edu.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icesi.edu.co.entity.Equiment;


public interface EquimentRepository extends JpaRepository<Equiment, Long> {
}