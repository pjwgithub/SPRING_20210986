package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.Article;
import com.example.demo.model.domain.Board;
import com.example.demo.model.repository.BlogRepository;
import com.example.demo.model.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // 생성자 자동 생성(부분)
public class BlogService {
    @Autowired // 객체 주입 자동화, 생성자 1개면 생략 가능
    private final BlogRepository blogRepository; // 리포지토리 선언

    public List<Article> findAll() { // 게시판 전체 목록 조회
        return blogRepository.findAll();
    }

    public Optional<Article> findById(Long id) { // 게시판 특정 글 조회
        return blogRepository.findById(id);
    }

    private final BoardRepository blogRepository2; // 리포지토리 선언
    
    public List<Board> findAllBoards() { // 게시판 전체 목록 조회
        return blogRepository2.findAll();
    }

    public Optional<Board> findByIdBoards(Long id) { // 게시판 특정 글 조회
        return blogRepository2.findById(id);
    }

    public Article save(AddArticleRequest request){
        // DTO가 없는 경우 이곳에 직접 구현 가능
        // public ResponseEntity<Article> addArticle(@RequestParam String title, @RequestParam String content) {
        // Article article = Article.builder()
        // .title(title)
        // .content(content)
        // .build();
        // return blogRepository.save(request.toEntity());
        return null;
    }

    public void update(Long id, AddArticleRequest request) {
        Optional<Article> optionalArticle = blogRepository.findById(id); // 단일 글 조회
        optionalArticle.ifPresent(article -> { // 값이 있으면
            article.update(request.getTitle(), request.getContent()); // 값을 수정
            blogRepository.save(article); // Article 객체에 저장
        });
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    // 10주차 연습문제 : 게시판 수정
    @Transactional // 수정 작업에는 트랜잭션 적용이 필수
    public Board updateBoard(Long id, AddArticleRequest request) {
        // 1. 수정할 Board 엔티티를 ID로 조회합니다.
        // findByIdBoards는 Optional<Board>를 반환합니다.
        Optional<Board> optionalBoard = blogRepository2.findById(id); 
        
        // 2. 게시글이 존재하지 않으면 예외를 발생시키거나 적절히 처리합니다.
        Board board = optionalBoard.orElseThrow(() -> 
            new IllegalArgumentException("게시글을 찾을 수 없습니다: " + id)
        );

        // 3. Board 엔티티의 update 메소드 호출 시, 
        //    DTO에서 받은 title, content와 **기존 board 객체**의 나머지 4개 필드 값을 전달합니다.
        //    (이는 Board.java의 update(String title, String content, String user, String newdate, String count, String likec) 시그니처를 따름)
        board.update(
            request.getTitle(), 
            request.getContent(), 
            board.getUser(),      // 기존 값 유지
            board.getNewdate(),   // 기존 값 유지 (수정일 처리는 Board 엔티티 내부에서 하는 것이 더 좋을 수 있습니다)
            board.getCount(),     // 기존 값 유지
            board.getLikec()      // 기존 값 유지
        );
        
        return board;
    }

    // Board 삭제 메소드 (새로 추가)
    public void deleteBoard(Long id) {
        blogRepository2.deleteById(id);
    }
}