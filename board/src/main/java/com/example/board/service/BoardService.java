package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.dto.BoardRequestDto;
import com.example.board.dto.BoardResponseDto;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    //게시글 조회 세부 작동
    //필수 항목 : 제목 작성자명 작성 날짜로 조회
    @Transactional
    public List<BoardResponseDto> findAllBoard() {
        //1. 전체 게시글을 작성일자순으로 찾아온다
        List<Board> allBoard = boardRepository.findAllByOrderByCreatedAtDesc();
        //2. 전체 게시글을 리턴해줘야하는 형식에 맞게 정제한다.
        //2-1 : BoardResponseDto를 담을 수 있는 리스트 생성
        List<BoardResponseDto> resultList = new ArrayList<>();
        //2-2 : for문을 통해서 필요한 객체 담기
        for (Board board : allBoard) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .title(board.getTitle())
                    .author(board.getAuthor())
                    .createAt(String.valueOf(board.getCreatedAt()))
                    .build();
            resultList.add(dto);
        }
        return resultList;
    }

    //비밀번호 확인 작동
    //필수 항목 : 등록된 비밀번호와 입력된 비밀번호가 맞는지 확인
    public boolean comparePassword(Long id, String passwordParam) {
        //1.비밀번호 및 아이디 가져 오기
        Board board = boardRepository.findById(id).orElseThrow( //가져온 값이 null일때 예외 발생
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        //2. 일치하면 true, 일치하지않으면 false를 받습니다
        if (passwordParam.equals(board.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    //업데이트 세부 작동
    @Transactional
    public Board update(Long id, BoardRequestDto requestDto) {
        //1. id로 게시글을 찾아옵니다.
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        //2. 찾아온 게시글을 update 합니다.
        Board updatedBoard = board.update(board, requestDto);

        // 3. 업데이트된 게시글을 리턴해줍니다.
        return updatedBoard;
    }


}
