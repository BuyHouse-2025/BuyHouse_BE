package com.ssafy.buyhouse.domain.board.dto.request;


import com.ssafy.buyhouse.domain.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {

    private String title;
    private String content;

    public Board toEntity() {
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
