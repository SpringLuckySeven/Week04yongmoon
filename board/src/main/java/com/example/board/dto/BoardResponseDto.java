package com.example.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardResponseDto {
    private String title;
    private String author;
    private String createAt;
}

