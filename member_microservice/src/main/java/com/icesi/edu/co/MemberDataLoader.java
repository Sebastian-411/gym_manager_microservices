package com.icesi.edu.co;

import com.icesi.edu.co.model.Member;
import com.icesi.edu.co.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MemberDataLoader implements CommandLineRunner {

    private final MemberRepository memberRepository;

    public MemberDataLoader(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (memberRepository.count() == 0) {
            Member member1 = new Member();
            member1.setName("Alice Johnson");
            member1.setEmail("alice.johnson@example.com");
            member1.setRegistrationDate("2024-01-15");

            Member member2 = new Member();
            member2.setName("Bob Smith");
            member2.setEmail("bob.smith@example.com");
            member2.setRegistrationDate("2024-02-20");

            Member member3 = new Member();
            member3.setName("Carol Davis");
            member3.setEmail("carol.davis@example.com");
            member3.setRegistrationDate("2024-03-10");

            memberRepository.save(member1);
            memberRepository.save(member2);
            memberRepository.save(member3);

            System.out.println("DataLoader: Miembros cargados en la base de datos.");
        } else {
            System.out.println("DataLoader: Miembros ya existentes, no se cargaron nuevos datos.");
        }
    }
}
