package com.example.demo.model.service;
import com.example.demo.model.domain.Board;

import lombok.*; // 어노테이션 자동 생성
// import com.example.demo.model.domain.Article;

@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Data // getter, setter, toString, equals 등 자동 생성
public class AddArticleRequest {
    private Long id; // 10주차 연습문제 : 게시판 수정
    
    private String title;
    private String content;
    private String user;
    private String newdate;
    private String count;
    private String likec;

    // public Article toEntity(){ // Article 객체 생성
    //     return Article.builder()
    //         .title(title)
    //         .content(content)
    //         .build();
    // }

    public Board toEntity(String currentUser){ // Board 객체 생성
        return Board.builder()
            .title(title)
            .content(content)
            .user(currentUser) // 13주차 과제 : 현재 로그인한 사용자 이름으로 설정
            .newdate(newdate)
            .count(count)
            .likec(likec)
            .build();
    }
}