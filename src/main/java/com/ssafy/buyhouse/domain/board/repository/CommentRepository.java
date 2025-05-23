package com.ssafy.buyhouse.domain.board.repository;

import com.ssafy.buyhouse.domain.board.domain.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
