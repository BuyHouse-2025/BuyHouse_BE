package com.ssafy.buyhouse.domain.board.controller;

import com.ssafy.buyhouse.domain.board.dto.request.CommentRequestDto;
import com.ssafy.buyhouse.domain.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/comments")
public class CommentController {
    private final CommentService commentService;

    // 댓글 작성
    @PostMapping
    public ResponseEntity<String> postComment(
            @RequestBody CommentRequestDto commentRequestDto) {
        System.out.println(commentRequestDto.getBoardId() + " " + commentRequestDto.getComment() + " " + commentRequestDto.getName());
        String result = commentService.addComment(commentRequestDto);
        return ResponseEntity.ok().body(result);
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(
            @PathVariable Long id,
            @RequestBody CommentRequestDto commentRequestDto) {

        String result = commentService.updateComment(id, commentRequestDto);
        return ResponseEntity.ok().body(result);
    }

    // 댓글 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePost(@PathVariable long id) {
        String result = commentService.delete(id);
        return ResponseEntity.ok().body(result);
    }
}
