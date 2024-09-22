package com.icesi.edu.co.service;

import com.icesi.edu.co.DTO.InscriptionDTO;
import com.icesi.edu.co.model.Member;
import com.icesi.edu.co.repository.MemberRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AmqpTemplate rabbiTemplate;

    public Member addMember(Member member) {
        Member savedMember = memberRepository.save(member);

        InscriptionDTO inscriptionDTO = new InscriptionDTO(member.getId().toString(), "New member registered: " + savedMember.getName());
        rabbiTemplate.convertAndSend("classes.exchange", "classes.routingkey", inscriptionDTO);
        return savedMember;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Boolean existsMember(Long id) {
        return memberRepository.existsById(id);
    }
}
