package com.ssafy.buyhouse.domain.board.dto.response;

import com.ssafy.buyhouse.domain.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostDetailResponseDto {

    private String title;
    private String content;
    private String name; // member 넣으면 String으로
    private LocalDateTime createdDate;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> comments;

    public static PostDetailResponseDto from(Board board) {
        return PostDetailResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .name(board.getMember().getName())
                .comments(board.getComments().stream().map(CommentResponseDto::from).collect(Collectors.toList()))
                .createdDate(board.getCreatedAt())
                .modifiedAt(board.getModifiedAt())
                .build();

    }
}
