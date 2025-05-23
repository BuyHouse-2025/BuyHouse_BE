package com.ssafy.buyhouse.domain.board.service;

import com.ssafy.buyhouse.domain.board.domain.Board;
import com.ssafy.buyhouse.domain.board.domain.Comment;
import com.ssafy.buyhouse.domain.board.dto.request.CommentRequestDto;
import com.ssafy.buyhouse.domain.board.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    final CommentRepository commentRepository;

    // 댓글작성
    public String addComment(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = CommentRequestDto.toEntity(commentRequestDto);
        comment = commentRepository.save(comment);
        return "댓글 작성이 완료 되었습니다.";
    }


    // 댓글수정
    public String updateComment(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 댓글입니다. id=" + id));

        comment.setComment(commentRequestDto.getComment());
        return "댓글 수정이 완료 되었습니다.";
    }


    //댓글삭제
    public String delete(long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 댓글입니다. id=" + id));

        commentRepository.delete(comment);

        return "게시물이 삭제되었습니다.";
    }

}
