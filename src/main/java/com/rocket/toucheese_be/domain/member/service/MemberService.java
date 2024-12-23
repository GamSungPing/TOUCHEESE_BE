package com.rocket.toucheese_be.domain.member.service;

import com.rocket.toucheese_be.domain.member.entity.Member;
import com.rocket.toucheese_be.domain.member.repository.MemberRepository;
import com.rocket.toucheese_be.global.response.CustomException;
import com.rocket.toucheese_be.global.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member findByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
    }

    @Transactional
    public void updateMemberName(Long memberId, String newName) {
//        if (memberRepository.existsByName(newName)) {
//            throw new IllegalArgumentException("이미 존재하는 이름입니다.");
//        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다."));
        member.setName(newName);
    }
}
