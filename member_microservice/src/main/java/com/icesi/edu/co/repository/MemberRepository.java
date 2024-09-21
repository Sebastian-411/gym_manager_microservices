package com.icesi.edu.co.repository;

import com.icesi.edu.co.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
