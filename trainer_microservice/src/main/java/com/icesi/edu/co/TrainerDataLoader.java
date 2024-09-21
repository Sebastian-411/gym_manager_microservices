package com.icesi.edu.co;

import com.icesi.edu.co.entity.Trainer;
import com.icesi.edu.co.repository.TrainerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TrainerDataLoader implements CommandLineRunner {

    private final TrainerRepository trainerRepository;

    public TrainerDataLoader(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (trainerRepository.count() == 0) {
            Trainer trainer1 = new Trainer();
            trainer1.setName("John Doe");
            trainer1.setExpertise("Yoga");

            Trainer trainer2 = new Trainer();
            trainer2.setName("Jane Smith");
            trainer2.setExpertise("Pilates");

            Trainer trainer3 = new Trainer();
            trainer3.setName("Mark Johnson");
            trainer3.setExpertise("Strength Training");

            trainerRepository.save(trainer1);
            trainerRepository.save(trainer2);
            trainerRepository.save(trainer3);

            System.out.println("DataLoader: Entrenadores cargados en la base de datos.");
        } else {
            System.out.println("DataLoader: Entrenadores ya existentes, no se cargaron nuevos datos.");
        }
    }
}
