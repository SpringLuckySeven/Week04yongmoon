package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
public class SignupRequestDto {
    private String username;
    private String password;
}