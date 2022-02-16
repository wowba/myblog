package com.sparta.myblog.service;

import com.sparta.myblog.dto.PostingRequestDto;
import com.sparta.myblog.model.Posting;
import com.sparta.myblog.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostingService {

    private final PostingRepository postingRepository;
    
    // 게시글 수정하기
    @Transactional
    public Long Edit(Long id, PostingRequestDto postingRequestDto) {
        Posting posting = postingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        posting.Edit(postingRequestDto);
        return id;
    }

    // 게시글 삭제하기
    @Transactional
    public Long Delete(Long id) {
        postingRepository.deleteById(id);
        return id;
    }
}