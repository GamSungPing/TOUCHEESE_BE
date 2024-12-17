package com.rocket.toucheese_be.domain.member.repository;

import com.rocket.toucheese_be.domain.member.entity.Member;
import com.rocket.toucheese_be.domain.member.entity.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}
