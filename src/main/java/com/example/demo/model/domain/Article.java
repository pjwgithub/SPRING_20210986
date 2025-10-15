package com.example.demo.model.domain;

import lombok.*;          // Lombok 어노테이션
import jakarta.persistence.*; // JPA 어노테이션

@Getter
@Entity                                 // DB 테이블 매핑
@Table(name = "article")                // 테이블 이름 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부 생성 방지

public class Article {
    @Id // 기본 키 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 자동 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false) // null 불가
    private String title = "";

    @Column(name = "content", nullable = false) // null 불가
    private String content = "";

    @Builder // 빌더 패턴 적용
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) { // 현재 객체 상태 업데이트
        this.title = title;
        this.content = content;
    }
}
