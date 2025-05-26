package com.ssafy.buyhouse.domain.board.dto.response;


import com.ssafy.buyhouse.domain.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedAt;

    public static PostResponseDto from(Board board) {
        return PostResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .createdDate(board.getCreatedAt())
                .modifiedAt(board.getModifiedAt())
                .build();
    }
}
