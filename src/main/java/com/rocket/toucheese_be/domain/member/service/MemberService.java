package com.rocket.toucheese_be.domain.member.service;

import com.rocket.toucheese_be.domain.member.entity.Member;
import com.rocket.toucheese_be.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Transactional
    public void save(Member member) {
        memberRepository.save(member);
    }

}
