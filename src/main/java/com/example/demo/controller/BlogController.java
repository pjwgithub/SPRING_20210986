package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.BlogService;

import com.example.demo.model.domain.Article;

@Controller // 컨트롤러 애너테이션 명시

public class BlogController {

    @Autowired
    private BlogService blogService;

    // @GetMapping("/article_list")
    // public String article_list() {
    //     return "article_list"; // templates/article_list.html 호출
    // }

    @GetMapping("/article_list") // 게시판 링크 지정
    public String article_list(Model model) {
        List<Article> list = blogService.findAll(); // 게시판 리스트
        model.addAttribute("articles", list); // 모델에 추가
        return "article_list"; // .HTML 연결
    }

    // 5주차 연습문제 : 게시글 저장 처리 및 리다이렉트 (기존 RestController 로직 반영)
    @PostMapping("/article_write") 
    public String saveArticle(@ModelAttribute AddArticleRequest request) {
        
        // 핵심 로직 재활용: blogService.save(request) 호출
        blogService.save(request); 

        // JSON 반환 대신 리다이렉트 문자열 반환
        return "redirect:/article_list";
    }
}
