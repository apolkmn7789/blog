package me.jangseunghun.springbootdeveloper.repository;

import me.jangseunghun.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article,Long> {
}
