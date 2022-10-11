package com.example.board.dto;

import com.example.board.domain.Timestamped;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class BoardRequestDto extends Timestamped {
    private final String title;
    private final String author;
    private final String content;
    private final String password;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public BoardRequestDto() {
        this.title = getTitle();
        this.author = getAuthor();
        this.content = getContent();
        this.password = getPassword();
        this.createdAt = getCreatedAt();
        this.modifiedAt = getModifiedAt();
    }
}

