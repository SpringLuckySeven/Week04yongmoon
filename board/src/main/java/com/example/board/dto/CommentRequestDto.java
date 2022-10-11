package com.example.board.dto;

import com.example.board.domain.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {

    private final String comment;

    private Comment board;

    private Comment user_id;

    public CommentRequestDto() {
        this.comment = getComment();
        this.board = getBoard();
        this.user_id =getUser_id();
    }
}
