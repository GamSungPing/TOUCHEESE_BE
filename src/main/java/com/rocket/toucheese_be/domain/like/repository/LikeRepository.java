package com.rocket.toucheese_be.domain.like.repository;

import com.rocket.toucheese_be.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    // 특정 멤버가 찜한 스튜디오 목록 조회
    List<Like> findByMemberId(Long memberId);

    // 특정 멤버가 특정 스튜디오를 찜했는지 확인
    boolean existsByMemberIdAndStudioId(Long memberId, Long studioId);

    // 찜 기록 삭제
    void deleteByMemberIdAndStudioId(Long memberId, Long studioId);
}
