package com.ssafy.buyhouse.domain.board.controller;

import com.ssafy.buyhouse.domain.auth.annotation.LoginUser;
import com.ssafy.buyhouse.domain.board.dto.request.PostRequestDto;
import com.ssafy.buyhouse.domain.board.dto.response.CommentResponseDto;
import com.ssafy.buyhouse.domain.board.dto.response.PostDetailResponseDto;
import com.ssafy.buyhouse.domain.board.dto.response.PostResponseDto;
import com.ssafy.buyhouse.domain.board.service.BoardService;
import com.ssafy.buyhouse.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 전체 게시물 페이지 페이징
    @GetMapping
    public ResponseEntity<Page<PostResponseDto>> getPostAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return ResponseEntity.ok(boardService.findAll(pageable));
    }

    // 검색 게시물 보기
    @GetMapping("/search")
    public ResponseEntity<Page<PostResponseDto>> getSearchPost(
            @RequestParam("keyword") String keyword,
            Pageable pageable) {
        return ResponseEntity.ok().body(boardService.searchPost(keyword, pageable));
    }

    // 단일 게시물 상세보기 -> 댓글 추가 완료 / 페이징 미완
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDetailResponseDto> getPost(@PathVariable Long id) {
        PostDetailResponseDto postDetailResponseDto = boardService.findById(id);
        return ResponseEntity.ok().body(postDetailResponseDto);
    }


    // 게시물 작성
    @PostMapping("/save")
    public ResponseEntity<?> savePost(@RequestBody PostRequestDto postRequestDto, @LoginUser Member member) {
        String result =  boardService.save(postRequestDto, member);
        return ResponseEntity.ok().body(result);
    }

    // 게시물 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody PostRequestDto postRequestDto,
            @LoginUser Member member) {
        try {
            String result = boardService.update(id, postRequestDto, member);
            return ResponseEntity.ok().body(result);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 게시물 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePost(
            @PathVariable long id,
            @LoginUser Member member) {

        try{
            String result = boardService.delete(id, member);
            return ResponseEntity.ok().body(result);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
