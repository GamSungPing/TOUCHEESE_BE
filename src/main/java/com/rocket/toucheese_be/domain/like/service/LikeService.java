package com.rocket.toucheese_be.domain.like.service;

import com.rocket.toucheese_be.domain.like.dto.LikeStudioListDto;
import com.rocket.toucheese_be.domain.like.entity.Like;
import com.rocket.toucheese_be.domain.like.repository.LikeRepository;
import com.rocket.toucheese_be.domain.member.entity.Member;
import com.rocket.toucheese_be.domain.member.repository.MemberRepository;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.studio.repository.StudioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final StudioRepository studioRepository;

    // 찜하기 기능
    @Transactional
    public void addLike(Long memberId, Long studioId) {
        if (likeRepository.existsByMemberIdAndStudioId(memberId, studioId)) {
            throw new IllegalStateException("이미 찜한 스튜디오입니다.");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("멤버를 찾을 수 없습니다."));

        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(() -> new EntityNotFoundException("스튜디오를 찾을 수 없습니다."));

        Like like = Like.builder()
                .member(member)
                .studio(studio)
                .build();

        likeRepository.save(like);
    }

    // 찜한 스튜디오 목록 조회
    @Transactional(readOnly = true)
    public List<LikeStudioListDto> getLikeStudios(Long memberId) {
        return likeRepository.findByMemberId(memberId).stream()
                .map(like -> new LikeStudioListDto(like.getStudio()))
                .toList();
    }

    // 찜 삭제
    @Transactional
    public void removeLike(Long memberId, Long studioId) {
        if (!likeRepository.existsByMemberIdAndStudioId(memberId, studioId)) {
            throw new IllegalStateException("찜한 기록이 없습니다.");
        }

        likeRepository.deleteByMemberIdAndStudioId(memberId, studioId);
    }
}