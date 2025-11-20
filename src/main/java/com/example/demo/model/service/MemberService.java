package com.example.demo.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated; // 12주차 연습문제 : 클래스 레벨 검증 활성화

import com.example.demo.model.domain.Member;
import com.example.demo.model.repository.MemberRepository;

import jakarta.validation.Valid; // 12주차 연습문제 : 파라미터 검증 수행
import lombok.RequiredArgsConstructor;

@Service
@Validated // 12주차 연습문제 : 클래스 레벨 검증 활성화
@Transactional // 트랜잭션 처리(클래스 내 모든 메소드 대상)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; // 스프링 버전 5 이후 단방향 해싱 알고리즘 지원

    private void validateDuplicateMember(AddMemberRequest request) {
        Member findMember = memberRepository.findByEmail(request.getEmail()); // 이메일 존재 유무 확인

        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    // 12주차 연습문제 : @Valid 추가, 데이터가 들어올 때 DTO에 설정된 규칙을 검사
    public Member saveMember(@Valid AddMemberRequest request) {
        validateDuplicateMember(request); // 이메일 중복 체크

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword); // 암호화된 비밀번호 설정

        return memberRepository.save(request.toEntity());
    }

    public Member loginCheck(String email, String rawPassword) {

        Member member = memberRepository.findByEmail(email); // 이메일 조회
        if (member == null) {
            throw new IllegalArgumentException("등록되지 않은 이메일입니다.");
        }

        if (!passwordEncoder.matches(rawPassword, member.getPassword())) { // 비밀번호 검증
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return member; // 인증 성공
    }
}
