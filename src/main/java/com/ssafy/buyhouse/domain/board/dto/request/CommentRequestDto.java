package com.ssafy.buyhouse.domain.board.dto.request;

import com.ssafy.buyhouse.domain.board.domain.Board;
import com.ssafy.buyhouse.domain.board.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {

    private String comment;
    // private String member; 멤버 추가후 수정
    private Board board;

    public static Comment toEntity(CommentRequestDto  commentRequestDto) {
        return Comment.builder()
                .comment(commentRequestDto.getComment())
                .board(commentRequestDto.getBoard())
                //.member(commentRequestDto.getMember())
                .build();
    }
}
