package com.sparta.myblog.api;

import com.sparta.myblog.dto.CommentResponseDto;
import com.sparta.myblog.dto.PostingRequestDto;
import com.sparta.myblog.dto.PostingResponseDto;
import com.sparta.myblog.model.Comment;
import com.sparta.myblog.model.Posting;
import com.sparta.myblog.repository.CommentRepository;
import com.sparta.myblog.repository.PostingRepository;
import com.sparta.myblog.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostingRestController {

    private final PostingRepository postingRepository;
    private final PostingService postingService;

    private final CommentRepository commentRepository;

    // 게시글 생성하기
    @PostMapping("/api/postings")
    public PostingResponseDto createPosting(@RequestBody PostingRequestDto postingRequestDto) {

        // requestDto를 사용해 게시글을 생성할 정보 가져다주기
        Posting posting = new Posting(postingRequestDto);

        // repository에 저장하기
        postingRepository.save(posting);

        // return 용 responseDto 제작후 리턴
        PostingResponseDto postingResponseDto = new PostingResponseDto(posting);
        return postingResponseDto;
    }

    // 전체 게시글 가져오기
    @GetMapping("/api/postings")
    public List<PostingResponseDto> getPostings() {

        // repository에서 게시글 생성날짜 순서로 가져오기
        List<Posting> postingList = postingRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt"));

        // return용 List<PostingResponseDto> 만들고 리턴해주기.
        List<PostingResponseDto> postingResponseDtoList = new ArrayList<>();
        for(Posting posting : postingList){
            PostingResponseDto postingResponseDto = new PostingResponseDto(posting);
            postingResponseDtoList.add(postingResponseDto);
        }
        return postingResponseDtoList;
    }

    // 선택한 게시글 조회하기
    @GetMapping("/api/postings/{id}")
    public PostingResponseDto getPosting(@PathVariable Long id){

        // @PathVariable을 사용해 어떤 게시판을 선택했는지 확인하기.
        Posting posting = postingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        // commentRepository에서 댓글 생성시간 순으로 가져오기
        List<Comment> commentList = commentRepository.findByPosting(posting, Sort.by(Sort.Direction.DESC,"createdAt"));
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for(Comment comment : commentList) {
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            commentResponseDtoList.add(commentResponseDto);
        }

        // return용 responseDto 사용
        PostingResponseDto postingResponseDto = new PostingResponseDto(posting, commentResponseDtoList);
        return postingResponseDto;
    }

    // 게시글 수정하기
    @PutMapping ("/api/postings/{id}")
    public Long editPosting(@RequestBody PostingRequestDto postingRequestDto, @PathVariable Long id) {
        return postingService.Edit(id, postingRequestDto);
    }
    
    // 게시글 삭제하기
    @DeleteMapping("/api/postings/{id}")
    public Long deletePosting(@PathVariable Long id){
        return postingService.Delete(id);
    }
}
