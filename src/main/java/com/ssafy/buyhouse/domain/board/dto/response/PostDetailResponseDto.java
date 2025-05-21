package com.ssafy.buyhouse.domain.board.dto.response;

import com.ssafy.buyhouse.domain.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailResponseDto {

    private String title;
    private String content;
    private String name; // member 넣으면 String으로
    private String creatTime;

    public static PostDetailResponseDto from(Board board) {
        return PostDetailResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                //  .name(board.getmember().getname())
                .name(board.getMember())
                .creatTime(board.getCreatTime())
                .build();

    }
}
