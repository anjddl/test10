package com.example.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/article")
    @ResponseBody
    public String index() {
        return "안녕하세요 Article에 오신걸 환영합니다";
    }
}
