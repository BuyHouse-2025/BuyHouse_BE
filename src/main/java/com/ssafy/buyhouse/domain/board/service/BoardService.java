package com.ssafy.buyhouse.domain.board.service;


import com.ssafy.buyhouse.domain.board.domain.Board;
import com.ssafy.buyhouse.domain.board.dto.request.PostRequestDto;
import com.ssafy.buyhouse.domain.board.dto.request.PostRequestDto;
import com.ssafy.buyhouse.domain.board.dto.response.PostListResponseDto;
import com.ssafy.buyhouse.domain.board.dto.response.PostResponseDto;
import com.ssafy.buyhouse.domain.board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    public final BoardRepository boardRepository;

    // 전체게시물 보기
//    public List<PostListResponseDto> findAll() {
//        List<PostListResponseDto> posts = boardRepository.findAll();
//        return posts;
//    }

    // 단일 게시물 보기
    public PostResponseDto findById(long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시물입니다. id=" + id));

        return PostResponseDto.from(board);
    }

    // 게시물 작성
    public String save(PostRequestDto postRequestDto) { // Member memeber
        Board board = postRequestDto.toEntity();
        // board.setMember(member);
        boardRepository.save(board);

        return "게시물이 저장되었습니다.";
    }

    // 게시물 수정
    @Transactional
    public String update(Long id, PostRequestDto postRequestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시물입니다. id=" + id));

        board.setTitle(postRequestDto.getTitle());
        board.setContent(postRequestDto.getContent());

        return "게시물이 수정되었습니다.";
    }

    // 게시물 삭제
    @Transactional
    public String delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시물입니다. id=" + id));

        boardRepository.delete(board);

        return "게시물이 삭제되었습니다.";
    }

}
