package com.spring.restAPI.repository;

import com.spring.restAPI.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
