package com.ssafy.buyhouse.domain.board.controller;

import com.ssafy.buyhouse.domain.board.domain.Board;

import com.ssafy.buyhouse.domain.board.dto.request.PostRequestDto;
import com.ssafy.buyhouse.domain.board.dto.response.PostListResponseDto;
import com.ssafy.buyhouse.domain.board.dto.response.PostResponseDto;
import com.ssafy.buyhouse.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 전체 게시물 페이지
//    @GetMapping
//    public ResponseEntity<List<PostListResponseDto>> listPosts() {
//        List<PostListResponseDto> posts = boardService.findAll();
//        return ResponseEntity.ok(posts);
//    }

    // 검색 게시물 보기
//    @GetMapping
//    public ResponseEntity<Board> getPost(@PathVariable Integer id) {
//        Board post = boardService.findById(id);
//        return ResponseEntity.ok(post);
//    }

    // 단일 게시물 보기
    @GetMapping("/{id}" )
    public ResponseEntity<PostResponseDto> getPost(@PathVariable long id) {
        PostResponseDto postResponseDto = boardService.findById(id);
        System.out.println("GET 요청 받음");
        return ResponseEntity.ok().body(postResponseDto);
    }

    // 게시물 작성
    @PostMapping("/save")
    public ResponseEntity<?> savePost(@RequestBody PostRequestDto postRequestDto) {
        String result =  boardService.save(postRequestDto);
        return ResponseEntity.ok().body(result);
    }

    // 게시물 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody PostRequestDto postRequestDto) {

        String result = boardService.update(id, postRequestDto);
        return ResponseEntity.ok().body(result);
    }

    // 게시물 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePost(@PathVariable long id) {

        String result = boardService.delete(id);
        return ResponseEntity.ok().body(result);
    }
}