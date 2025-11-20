package com.example.demo.model.service;

import com.example.demo.model.domain.Member;
import jakarta.validation.constraints.*;
import lombok.*; // 어노테이션 자동 생성

@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Data // getter, setter, toString, equals 등 자동 생성
public class AddMemberRequest {

    // 12주차 연습문제 : 회원가입 유효성 검사
    // 1. 이름: 공백 x, 특수문자 x (한글, 영문, 숫자만 허용하는 정규식)
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]+$", message = "이름에는 특수문자나 공백을 포함할 수 없습니다.")
    private String name;

    // 2. 이메일: 공백 x, 이메일 형식
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    // 3. 패스워드: 8글자 이상, 대소문자 포함
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "비밀번호는 8자 이상이어야 하며, 대문자와 소문자를 모두 포함해야 합니다.")
    private String password;

    // 4. 나이: 19세 이상 ~ 90세 이하
    @NotBlank(message = "나이는 필수 입력 값입니다.")
    @Pattern(regexp = "^(19|[2-8][0-9]|90)$", message = "나이는 19세 이상 90세 이하만 가능합니다.")
    private String age;

    // 5. 모바일: 공백 o (제한 없음)
    private String mobile;

    // 6. 주소: 공백 o (제한 없음)
    private String address;

    public Member toEntity() { // Member 생성자를 통해 객체 생성
        return Member.builder()
            .name(name)
            .email(email)
            .password(password)
            .age(age)
            .mobile(mobile)
            .address(address)
            .build();
    }
}
