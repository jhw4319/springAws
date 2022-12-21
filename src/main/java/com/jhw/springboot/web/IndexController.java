package com.jhw.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index"; // 앞의 경로와 뒤의 파일 확장자는 자동으로 지정
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
