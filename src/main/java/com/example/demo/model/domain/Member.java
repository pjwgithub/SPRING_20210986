package com.example.demo.model.domain;

import lombok.*;          // Lombok 어노테이션
import jakarta.persistence.*; // JPA 어노테이션

@Getter
@Entity                                 // DB 테이블 매핑
@Table(name = "member")                // 테이블 이름 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부 생성 방지

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 1씩 증가
    @Column(name = "id", updatable = false) // 수정 x
    private Long id;
    @Column(name = "name", nullable = false) // null x
    private String name = "";
    @Column(name = "email", unique = true, nullable = false) // unique 중복 x
    private String email = "";
    @Column(name = "password", nullable = false)
    private String password = "";
    @Column(name = "age", nullable = false)
    private String age = "";
    @Column(name = "mobile", nullable = false)
    private String mobile = "";
    @Column(name = "address", nullable = false)
    private String address = "";
    @Builder // 생성자에 빌더 패턴 적용(불변성)
    public Member(String name, String email, String password, String age, String mobile, String address){
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.mobile = mobile;
        this.address = address;
    }
}
