package com.example.board;

import com.example.board.domain.Board;
import com.example.board.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

    //h2 콘솔 사용시 초기 데이터 생성
    @Bean
    public CommandLineRunner demo(BoardRepository boardRepository) {
        return (args) -> {
            boardRepository.save(new Board("1111", "22222", "3333", "1234"));
        };

    }
}
