package com.example.demo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.TestDB;
import com.example.demo.model.repository.TestRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    @Autowired
    private TestRepository testRepository;

    // 이름으로 1명 찾기
    public TestDB findByName(String name) {
        return testRepository.findByName(name);
    }

    // 전체 사용자 조회
    public List<TestDB> findAllUsers() {
        return testRepository.findAll();
    }
}
