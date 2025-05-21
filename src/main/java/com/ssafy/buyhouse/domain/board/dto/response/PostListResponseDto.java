package com.ssafy.buyhouse.domain.board.dto.response;


import com.ssafy.buyhouse.domain.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostListResponseDto {

    private String title;
    private String name;
    private String creatTime;

//    public PostListResponseDto from(PostResponseDto postResponseDto) {
//        return PostListResponseDto.builder()
//                .title(postResponseDto.getTitle())
//                .creatTime(postResponseDto.getCreatTime())
//                .build();
//
//    }
}
