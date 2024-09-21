package com.icesi.edu.co;

import com.icesi.edu.co.entity.Equiment;
import com.icesi.edu.co.repository.EquimentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EquimentDataLoader implements CommandLineRunner {

    private final EquimentRepository equimentRepository;

    public EquimentDataLoader(EquimentRepository equimentRepository) {
        this.equimentRepository = equimentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (equimentRepository.count() == 0) {
            Equiment equiment1 = new Equiment();
            equiment1.setName("Equiment A");
            equiment1.setDescription("Description for Equiment A");
            equiment1.setAmount(5);

            Equiment equiment2 = new Equiment();
            equiment2.setName("Equiment B");
            equiment2.setDescription("Description for Equiment B");
            equiment2.setAmount(10);

            Equiment equiment3 = new Equiment();
            equiment3.setName("Equiment C");
            equiment3.setDescription("Description for Equiment C");
            equiment3.setAmount(15);

            equimentRepository.save(equiment1);
            equimentRepository.save(equiment2);
            equimentRepository.save(equiment3);

            System.out.println("DataLoader: Equipos cargados en la base de datos.");
        } else {
            System.out.println("DataLoader: Equipos ya existentes, no se cargaron nuevos datos.");
        }
    }
}
