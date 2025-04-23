package me.jangseunghun.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.jangseunghun.springbootdeveloper.domain.Article;
import me.jangseunghun.springbootdeveloper.dto.AddArticleRequest;
import me.jangseunghun.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// final 이 붙거나 @NotNull 이 붙은 필드의 생성자 추가
@RequiredArgsConstructor
@Service // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }
    // 데이터 베이스에 있는 모든 글을 가져오는 메서드
    public List<Article> findAll(){
        return blogRepository.findAll();
    }
}
