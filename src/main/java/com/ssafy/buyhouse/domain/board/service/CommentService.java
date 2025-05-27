package com.ssafy.buyhouse.domain.board.service;

import com.ssafy.buyhouse.domain.board.domain.Board;
import com.ssafy.buyhouse.domain.board.domain.Comment;
import com.ssafy.buyhouse.domain.board.dto.request.CommentRequestDto;
import com.ssafy.buyhouse.domain.board.repository.BoardRepository;
import com.ssafy.buyhouse.domain.board.repository.CommentRepository;
import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    // 댓글작성
    public String addComment(CommentRequestDto commentRequestDto) {
        Member member = memberRepository.findByName(commentRequestDto.getName())
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        Board board = boardRepository.findById(commentRequestDto.getBoardId())
                .orElseThrow(() -> new EntityNotFoundException("Board not found"));

        Comment comment = Comment.builder().comment(commentRequestDto.getComment()).member(member).board(board).build();
        commentRepository.save(comment);
        return "댓글 작성이 완료 되었습니다.";
    }


    // 댓글수정
    @Transactional
    public String updateComment(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 댓글입니다. id=" + id));

        comment.setComment(commentRequestDto.getComment());
        return "댓글 수정이 완료 되었습니다.";
    }


    //댓글삭제
    @Transactional
    public String delete(long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 댓글입니다. id=" + id));

        commentRepository.delete(comment);

        return "게시물이 삭제되었습니다.";
    }

}
