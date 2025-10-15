package com.example.demo.controller; // BlogController와 같은 패키지

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

//전역 예외 처리 클래스
//모든 Controller에서 발생하는 특정 예외를 처리합니다.

@ControllerAdvice
public class GlobalExceptionHandler {

    //URL 경로 변수(Path Variable)의 타입 불일치 예외를 처리합니다.
    //예: /article_edit/6444fff 와 같이 Long을 기대하는데 문자열이 입력된 경우
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ModelAndView handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        
        System.err.println("❌ 타입 불일치 오류 발생: 기대 타입=" + ex.getRequiredType().getSimpleName() + ", 입력 값=" + ex.getValue());

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        // 새로운 예외용 에러 페이지 템플릿 이름 지정
        // 파일 구조에 맞게 'error_page/' 경로를 포함합니다.
        mav.setViewName("error_page/article_type_error"); 
        
        return mav;
    }
    
    // 이외의 모든 일반 예외를 처리하는 핸들러 (선택적)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception ex) {
        System.err.println("일반 예외 발생: " + ex.getMessage());
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        // 기존의 에러 페이지 사용
        mav.setViewName("error_page/article_error"); 
        return mav;
    }
}