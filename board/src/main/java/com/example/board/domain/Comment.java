package com.example.board.domain;

import com.example.board.dto.CommentRequestDto;
import com.example.board.service.CommentService;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Comment {

    @GeneratedValue(strategy = GenerationType.AUTO) //자동으로 숫자가 늘어나게 만들어주고
    @Id
    @Column
    private Long id;

    @Column(nullable = false)
    String content;

    @ManyToOne //댓글 입장에서는 한개의 게시글이 여러 댓글을 가질수 있음
    @JoinColumn(name="boardId")
    private Board board;

    @ManyToOne //댓글 입장에서는 한명의 User가 여러 댓글을 가질수 있음
    @JoinColumn(name="userId")
    private User user;

    public Comment(Long id, String content, Board board, User user) {
        this.id = id;
        this.content = content;
        this.board = board;
        this.user = user;
    }

    public Comment(CommentRequestDto requestDto) {
        this.id = getId();
        this.user = getUser();
        this.content = getContent();
        this.board = getBoard();

    }


}
