package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //컨트롤러 애너테이션 명시
public class DemoController { 
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
}