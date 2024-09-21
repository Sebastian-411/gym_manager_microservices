package com.icesi.edu.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.edu.co.entity.Equiment;
import com.icesi.edu.co.repository.EquimentRepository;

@Service
public class EquimentService {
    @Autowired
    private EquimentRepository equimentRepository;

    public Equiment addEquiment(Equiment equiment) {
        return equimentRepository.save(equiment);
    }

    public List<Equiment> getAllEquiments() {
        return equimentRepository.findAll();
    }


}
