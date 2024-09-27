package com.example.sbb.Article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class ArticleController {

   private final ArticleRepository articleRepository;

    @GetMapping("/Article/list")
    public String list(Model model) {
        List<Article> questionList = this.articleRepository.findAll();
        model.addAttribute("articleList", questionList);
        return "Aritcle_list";
    }
}
