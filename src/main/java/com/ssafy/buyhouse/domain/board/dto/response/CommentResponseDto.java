package com.ssafy.buyhouse.domain.board.dto.response;

import com.ssafy.buyhouse.domain.board.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

    private String comment;
    private LocalDateTime createdDate;
    private String name;

    public static CommentResponseDto from(Comment comment) {
        return CommentResponseDto.builder()
                .comment(comment.getComment())
                .createdDate(comment.getCreatedAt())
                //.name(comment.getMember.getName()) // 멤버 추가후 수정
                .build();
    }
}
