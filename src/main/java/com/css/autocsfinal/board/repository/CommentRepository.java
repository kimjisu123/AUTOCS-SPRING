package com.css.autocsfinal.board.repository;

import com.css.autocsfinal.board.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<BoardComment, Integer> {
    

}
