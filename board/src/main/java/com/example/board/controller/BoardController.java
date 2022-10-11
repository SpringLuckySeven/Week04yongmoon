package com.example.board.controller;

import com.example.board.domain.Board;
import com.example.board.dto.BoardRequestDto;
import com.example.board.dto.BoardResponseDto;
import com.example.board.repository.BoardRepository;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    //생성 API
    @PostMapping("/api/boards")
    public Board createBoard(@RequestBody BoardRequestDto requestDto) {
        //1. BoardRequestDto를 파라미터로 받는다.
        //2. Board 객체를 생성한다.
        Board board = new Board(requestDto);
        //3. DB에 접근해서 저장한 후 게시글을 다시 객체에 할당합니다.
        Board resultBoard = boardRepository.save(board);
        //4. 저장된 보드를 리턴합니다.
        return resultBoard;
    }

    //전체 조회 API
    @GetMapping("/api/boards")
    public List<BoardResponseDto> getBoards() {
        // 1. 전체 게시글을 찾아옵니다.
        List<BoardResponseDto> findedAllBoard =  boardService.findAllBoard();

        // 2. 찾아온 전체 게시글을 리턴합니다.
        return findedAllBoard;
    }

    //상세 조회 API
    @GetMapping("/api/boards/{id}")
    public Board gerDetailBoard(@PathVariable long id) {
        //1. id에 맞는 게시글 불러옵니다.
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        //2. 만약 해당 게시글이 없다면 message를 띄워줍니다.

        return board;
    }

    //비밀번호 확인
    @GetMapping("/api/boards/{id}/{password}")
    public boolean comparePassword(@PathVariable Long id,@PathVariable String password){
        //1. 아이디 객체 생성
        Long paramId = id;
        //2. 비밀번호 객체 생성
        String passwordParam = password;

        //3. id로 게시글을 찾아 파라미터로 할당받은 패스워드와 비교 후 일치하면 true , 일치하지 않으면 false를 리턴합니다.
        boolean result = boardService.comparePassword(paramId, passwordParam);

        return result;
    }

    //삭제 API
    @DeleteMapping("/api/boards/{id}")
    public String deleteBoard(@PathVariable Long id) {
        // 정상적으로 삭제가 진행됐음을 알 수 있게끔하는 값 return
        // 정상적으로 삭제가 되지 않았음을 알 수 있게끔 하는 값 return -> 오늘 시험에서 요구한 400입니다.

        try{
            //여기를 먼저 실행하다가
            boardRepository.deleteById(id);
            //성공하면 리턴합니다.
            return "sucess";

        }catch (Exception e){
            //익셉션이나면 얘가 실행됩니다. 404 에러
            return String.valueOf(HttpStatus.BAD_REQUEST);
        }

    }

    //업데이트 API
    @PutMapping("/api/boards/{id}")
    public Board updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        //1. id로 게시글을 찾아 찾은 게시글을 requestDto에 있는 값들로 업데이트해준 후 업데이트된 게시글을 리턴받습니다.
        Board updatedBoard = boardService.update(id, requestDto);

        //2. 업데이트된 게시글의 내용을 리턴합니다.
        return updatedBoard;
    }

//    //작성 날짜 순으로 게시글을 전체 게시글을 조회합니다.
//    @GetMapping("/api/boards/desc")
//    public List<Board> findedAllBoardDesc() {
//
//        List<Board> allByOrderByModifiedAtDesc = boardRepository.findAllByOrderByCreatedAtDesc();
//
//        return allByOrderByModifiedAtDesc;
//    }

}

