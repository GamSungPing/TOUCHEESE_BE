package com.rocket.toucheese_be.domain.member.repository;

import com.rocket.toucheese_be.domain.member.entity.Member;
import com.rocket.toucheese_be.domain.member.entity.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId);

    boolean existsByName(String name);

    // 더티 체킹 방지
    @Modifying
    @Query("UPDATE Member m SET m.name = :name WHERE m.id = :id")
    void updateName(@Param("id") Long id, @Param("name") String name);
}
