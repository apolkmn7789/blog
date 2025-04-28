package me.jangseunghun.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

/* 생성시간과 수정시간 추가 04/28 */
@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    // 기본키를 자동으로 1씩 증가
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    // title 이라는 not null 컬럼과 매핑
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate // 엔티티가 생성 될 때 생성 시간 저장
    @Column(name= "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // 엔티티가 수정 될 때 수정 시간 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder // 빌더 패턴으로 객체 생성
    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
