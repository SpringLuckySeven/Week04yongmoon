package com.example.board.controller;

import com.example.board.domain.Comment;
import com.example.board.dto.CommentRequestDto;
import com.example.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentRepository commentRepository;

    //댓글 생성 API
    @PostMapping("/api/boards/{id}/comment")
    public Comment createComment(@RequestBody CommentRequestDto commentRequestDto) {
        //1. CommentRequestDto를 파라미터로 받는다.
        //2. Comment 객체를 생성한다.
        Comment comment = new Comment(commentRequestDto);
        //3. DB 접근하여 저장한후 댓글을 다시 객체에 할당한다.
        Comment resultcomment = commentRepository.save(comment);
        //4. 저장된 객체를 리턴한다.
        return resultcomment;
    }

    //댓글 삭제 API
    @DeleteMapping("/api/boards/{id}/comment") //URL 설정 확인 필요
    public String deleteComment(@PathVariable Long id) {
        //1. 저장된 댓글을 불러온다.
        //2. 정상적으로 댓글이 삭제되었음을 알수있는 값 return
        //3. 정상적으로 댓글이 삭제되지 않았음을 알수있는 값 return
        try {
            //여기를 먼저 실행하다가
            commentRepository.deleteById(id);
            //성공하면 리턴합니다.
            return "sucess";

        } catch (Exception e) {
            //익셉션이나면 얘가 실행됩니다. 404 에러
            return String.valueOf(HttpStatus.BAD_REQUEST);

        }


    }
}