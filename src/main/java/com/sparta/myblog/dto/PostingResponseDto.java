package com.sparta.myblog.dto;

import com.sparta.myblog.model.Posting;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostingResponseDto {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private List<CommentResponseDto> comments;
    private LocalDateTime createdAt;

    // 처음에 게시글 생성할 때, 전체 게시판 조회할 때 사용하는 생성자
    public PostingResponseDto(Posting posting) {
        this.id = posting.getId();
        this.title = posting.getTitle();
        this.writer = posting.getWriter();
        this.content = posting.getContent();
        this.createdAt = posting.getCreatedAt();
    }

    // 게시글을 가져올 때 사용하는 생성자
    public PostingResponseDto(Posting posting, List<CommentResponseDto> commentResponseDtoList) {
        this.id = posting.getId();
        this.title = posting.getTitle();
        this.writer = posting.getWriter();
        this.content = posting.getContent();
        this.createdAt = posting.getCreatedAt();
        this.comments = commentResponseDtoList;
    }
}
