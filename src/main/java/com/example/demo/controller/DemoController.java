package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.model.service.TestService; // 최상단 서비스 클래스 연동 추가

import com.example.demo.model.domain.TestDB;

@Controller //컨트롤러 애너테이션 명시
public class DemoController { 
    @Autowired
    TestService testService; // DemoController 클래스 아래 객체 생성
    @GetMapping("/hello") //전송방식 GET
    public String hello(Model model) {
        model.addAttribute("data", "반갑습니다."); //model설정
        return "hello"; //hello.html연결
    }
     @GetMapping("/hello2") //2주차 연습문제
    public String hello2(Model model) {
        // 5개의 속성(변수) 추가
        model.addAttribute("data", "박재완님,");
        model.addAttribute("data2", "오늘");
        model.addAttribute("data3", "날씨는");
        model.addAttribute("data4", "매우");
        model.addAttribute("data5", "좋습니다.");
        return "hello2"; // hello2.html로 이동
    }

    @GetMapping("/about_detailed")
    public String about() {
        return "about_detailed"; // about_detailed.html로 이동
    }

    @GetMapping("/test1")
    public String thymeleaf_test1(Model model) {
    model.addAttribute("data1", "<h2> 방갑습니다 </h2>");
    model.addAttribute("data2", "태그의 속성 값");
    model.addAttribute("link", 01);
    model.addAttribute("name", "홍길동");
    model.addAttribute("para1", "001");
    model.addAttribute("para2", 002);
        return "thymeleaf_test1";
    }
    // 하단에 맵핑 이어서 추가
    @GetMapping("/testdb")
    public String getAllTestDBs(Model model) {
        // 4주차 연습문제
        List<TestDB> users = testService.findAllUsers();  
        model.addAttribute("users", users);
        return "testdb";
    }

}
