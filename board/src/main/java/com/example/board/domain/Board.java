package com.example.board.domain;

import com.example.board.dto.BoardRequestDto;
import com.example.board.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Board extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.

    @GeneratedValue(strategy = GenerationType.AUTO) //자동으로 숫자가 늘어나게 만들어주고
    @Id
    @Column( name = "post_id")
    private Long id;

    //테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 되지만, 기본값외에 추가로 변경이 필요한 옵션이 있다면 사용한다.
    //문자열의 경우 VARCHAR(255)가 기본값인데, 위처럼 length나 타입을 변경하고 싶다면 Column을 선언하여 변경할 수 있다.
    @Column(nullable = false) //객체필드를 테이블의 컬럼에 매핑 시켜주는 항목 null값을 사용할수 없다는 조건이 있다.
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "board")
    private List<Comment> comment;



    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }

    public Board(String title, String author, String content, String password) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.password = password;
    }

    public Board update(Board board , BoardRequestDto requestDto) {
        board.title = requestDto.getTitle();
        board.author = requestDto.getAuthor();
        board.content = requestDto.getContent();
        board.password = requestDto.getPassword();

        return board;
    }




}