package com.ssafy.buyhouse.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {

    private String comment;
    private String name;
    private Long   boardId;

}
