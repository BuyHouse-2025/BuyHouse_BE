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

    private Long id;
    private String comment;
    private LocalDateTime createdDate;
    // private String member; 멤버 추가후 수정
    private Board board;

    public static Comment toEntity(CommentRequestDto  commentRequestDto) {
        return new Comment();
        /*return Comment.builder()
                .id(commentRequestDto.getId())
                .comment(commentRequestDto.getComment())
                .createdDate(commentRequestDto.getCreatedDate())
                // .member(commentRequestDto.getMember())
                .board(commentRequestDto.getBoard())
                .build();*/
    }
}
