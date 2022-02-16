package com.sparta.myblog.repository;

import com.sparta.myblog.model.Comment;
import com.sparta.myblog.model.Posting;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //게시글에서 댓글 가져오는거 만들기
    List<Comment> findByPosting(Posting posting, Sort sort);
}
