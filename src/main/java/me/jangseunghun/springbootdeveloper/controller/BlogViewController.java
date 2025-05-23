package me.jangseunghun.springbootdeveloper.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.jangseunghun.springbootdeveloper.domain.Article;
import me.jangseunghun.springbootdeveloper.dto.ArticleListViewResponse;
import me.jangseunghun.springbootdeveloper.dto.ArticleViewResponse;
import me.jangseunghun.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles",articles); // 블로그 글 리스트 저장

        return "articleList"; // articles.html 뷰 조회
    }
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }
    @GetMapping("/new-article")
    // id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑(id 는 없을수도 있음)
    public String newArticle(@RequestParam(required = false) Long id,Model model){
        if(id == null){ // id 가 없으면
            model.addAttribute("article",new ArticleViewResponse());// 생성
        }else{ // 있으면
            Article article = blogService.findById(id);
            model.addAttribute("article",new ArticleViewResponse(article)); // 수정
        }
        return "newArticle";
    }
}
