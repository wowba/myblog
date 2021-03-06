package com.sparta.myblog.api;

import com.sparta.myblog.dto.CommentRequestDto;
import com.sparta.myblog.model.Comment;
import com.sparta.myblog.model.Posting;
import com.sparta.myblog.repository.CommentRepository;
import com.sparta.myblog.repository.PostingRepository;
import com.sparta.myblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentRestController {

    private final PostingRepository postingRepository;

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    // 댓글 생성하기 ( 에러메세지 리턴하기 )
    @PostMapping("/api/postings/{id}/comments")
    public @ResponseBody String createComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long id){
        // 댓글 다는 게시판 가져오기
        Posting posting = postingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        // 댓글 repo에 저장하기
        Comment comment = new Comment(commentRequestDto, posting);
        // 댓글 내용이 공백인지 확인
        if(comment.getComment() == ""){
            return "댓글 내용을 입력해주세요.";
        }
        commentRepository.save(comment);
        return "댓글이 작성되었습니다.";
    }

    // 댓글 수정하기
    @PutMapping("/api/comments/{id}")
    public @ResponseBody String editComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long id){
        return commentService.Edit(id, commentRequestDto);
    }

    // 댓글 삭제하기
    @DeleteMapping("/api/comments/{id}")
    public Long deleteComment(@PathVariable Long id) {
        return commentService.Delete(id);
    }
}