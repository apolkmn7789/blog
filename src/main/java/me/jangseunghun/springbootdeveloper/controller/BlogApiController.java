package me.jangseunghun.springbootdeveloper.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.jangseunghun.springbootdeveloper.domain.Article;
import me.jangseunghun.springbootdeveloper.dto.AddArticleRequest;
import me.jangseunghun.springbootdeveloper.dto.AddUserRequest;
import me.jangseunghun.springbootdeveloper.dto.ArticleResponse;
import me.jangseunghun.springbootdeveloper.dto.UpdateArticleRequest;
import me.jangseunghun.springbootdeveloper.service.BlogService;
import me.jangseunghun.springbootdeveloper.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
// http Response Body 에 객체 데이터를 JSON 형식으로 변환하는 컨트롤러
@RestController
public class BlogApiController {

    private final UserService userService;
    private final BlogService blogService;

    // HTTP 메서드가 POST 일 때 전달받은 URL 과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    // @RequestBody 로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);
        // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return  ResponseEntity.ok()
                .body(articles);
    }
    @GetMapping("/api/articles/{id}")
    // URL 경로엑서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable("id") Long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }
    @DeleteMapping("api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id){
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
    @PutMapping("api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") Long id,
                                                 @RequestBody UpdateArticleRequest request){
        Article updatedArticle = blogService.update(id,request);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }
    @PostMapping("/user")
    public String signup(AddUserRequest request){
        userService.save(request); // 회원가입 메서드 호출
        return "redirect:/login"; // 호원가입이 완료되면 로그인 페이지로 이동
    }
}
