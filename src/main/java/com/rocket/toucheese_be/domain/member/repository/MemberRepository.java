package com.rocket.toucheese_be.domain.member.repository;

import com.rocket.toucheese_be.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByName(String name);
}