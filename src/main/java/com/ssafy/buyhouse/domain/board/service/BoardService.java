package com.ssafy.buyhouse.domain.board.service;


import com.ssafy.buyhouse.domain.board.domain.Board;
import com.ssafy.buyhouse.domain.board.dto.request.PostRequestDto;
import com.ssafy.buyhouse.domain.board.dto.response.PostDetailResponseDto;
import com.ssafy.buyhouse.domain.board.dto.response.PostResponseDto;
import com.ssafy.buyhouse.domain.board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    // 전체게시물 보기
    public Page<PostResponseDto> findAll(Pageable pageable) {

        Page<Board> boards = boardRepository.findAll(pageable);

        // board를 response객체로 변환
        return boards.map(board ->
                PostResponseDto.builder()
                        .title(board.getTitle())
                        .name(board.getMember().getName()) // 맴버넣으면 board.getMember().getName()
                        .build());
    }

    // 검색 게시물 보기
    public Page<PostResponseDto> searchPost(String keyword, Pageable pageable) {

        Page<Board> Searchboards = boardRepository.findByTitleContaining(keyword, pageable);

        return Searchboards.map(board ->
                PostResponseDto.builder()
                        .title(board.getTitle())
                        .name(board.getMember().getName()) // 맴버넣으면 board.getMember().getName()
                        .build());
    }

    // 단일 게시물 상세 보기
    public PostDetailResponseDto findById(long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시물입니다. id=" + id));

        return PostDetailResponseDto.from(board);
    }

    // 게시물 작성
    @Transactional
    public String save(PostRequestDto postRequestDto) { // Member memeber
        Board board = postRequestDto.toEntity(postRequestDto);
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
