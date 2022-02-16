package com.sparta.myblog.model;

import com.sparta.myblog.dto.PostingRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Posting extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "posting", cascade = {CascadeType.ALL})
    // 양방향 매핑일 때 mappedBy 사용
    // "posting"인 이유는 Comment 에서 Posting 객체의 posting을 쓰기 때문이다.
    private List<Comment> commentList = new ArrayList<>();

    // 게시글 생성
    public Posting(PostingRequestDto postingRequestDto) {
        this.title = postingRequestDto.getTitle();
        this.writer = postingRequestDto.getWriter();
        this.content = postingRequestDto.getContent();
    }

    // 게시글 수정
    public void Edit(PostingRequestDto postingRequestDto) {
        this.title = postingRequestDto.getTitle();
        this.writer = postingRequestDto.getWriter();
        this.content = postingRequestDto.getContent();
    }
}
