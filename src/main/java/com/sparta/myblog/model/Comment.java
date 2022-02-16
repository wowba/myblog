package com.sparta.myblog.model;


import com.sparta.myblog.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne // 댓글은 여러개, 게시글은 하나.
    @JoinColumn(name="posting_id", nullable = false)
    private Posting posting;

    // 댓글 생성
    public Comment(CommentRequestDto commentRequestDto, Posting posting) {
        this.comment = commentRequestDto.getComment();
        this.posting = posting;
    }

    // 댓글 수정
    public void Edit(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}
