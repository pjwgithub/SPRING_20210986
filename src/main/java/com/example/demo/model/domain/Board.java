package com.example.demo.model.domain;

import lombok.*;          // Lombok 어노테이션
import jakarta.persistence.*; // JPA 어노테이션

@Getter
@Entity                                 // DB 테이블 매핑
@Table(name = "board")                // 테이블 이름 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부 생성 방지

public class Board {
    @Id // 기본 키 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 자동 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false) // null 불가
    private String title = "";

    @Column(name = "content", nullable = false) // null 불가
    private String content = "";

    @Column(name = "user", nullable = false) // 이름
    private String user = "";

    @Column(name = "newdate", nullable = false) // 날짜
    private String newdate = "";

    @Column(name = "count", nullable = false) // 조회수
    private String count = "";

    @Column(name = "likec", nullable = false) // 좋아요
    private String likec = "";

    @Builder // 빌더 패턴 적용
    public Board(String title, String content, String user, String newdate, String count, String likec) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.newdate = newdate;
        this.count = count;
        this.likec = likec;
    }

    public void update(String title, String content, String user, String newdate, String count, String likec) { // 현재 객체 상태 업데이트
        this.title = title;
        this.content = content;
        this.user = user;
        this.newdate = newdate;
        this.count = count;
        this.likec = likec;
    }
    
}
