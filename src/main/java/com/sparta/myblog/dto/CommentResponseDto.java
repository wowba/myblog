package com.sparta.myblog.dto;

import com.sparta.myblog.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String comment;
    private LocalDateTime createdAt;

    // 댓글 생성할때 쓰는 생성자
    public CommentResponseDto(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
    
    // 게시판을 불러올 때 사용하는 생성자
    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createdAt = comment.getCreatedAt();
    }
}
