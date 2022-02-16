package com.sparta.myblog.service;

import com.sparta.myblog.dto.CommentRequestDto;
import com.sparta.myblog.model.Comment;
import com.sparta.myblog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    
    // 댓글 수정하기
    @Transactional
    public Long Edit(Long id, CommentRequestDto commentRequestDto) throws Exception {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        // 댓글 내용이 공백인지 확인
        if(commentRequestDto.getComment() == ""){
            throw new Exception ("댓글 내용을 입력해주세요");
        }
        comment.Edit(commentRequestDto);
        return id;
    }

    // 댓글 삭제하기
    @Transactional
    public Long Delete(Long id) {
        commentRepository.deleteById(id);
        return id;
    }
}