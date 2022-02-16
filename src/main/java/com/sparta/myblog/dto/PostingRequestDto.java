package com.sparta.myblog.dto;

import lombok.Getter;

@Getter
public class PostingRequestDto {
    private String title;
    private String writer;
    private String content;
}
