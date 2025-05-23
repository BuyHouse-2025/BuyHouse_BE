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

    private String title;
    private String name;
    private LocalDateTime creatTime;

    public static PostResponseDto from(Board board) {
        return PostResponseDto.builder()
                .title(board.getTitle())
                .creatTime(board.getCreatedAt())
                .build();
    }
}
