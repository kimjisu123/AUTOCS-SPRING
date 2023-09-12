package com.css.autocsfinal.board.repository;

import com.css.autocsfinal.board.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<BoardComment, Integer> {


    //댓글 번호로 삭제
    int deleteByCommentNo(int commentNo);

    BoardComment findByCommentNo(int commentNo);
}
