package com.css.autocsfinal.board.repository;

import com.css.autocsfinal.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository  extends JpaRepository<Board, Integer> {

    @Query(value = "SELECT MAX(boardNo) FROM Board")
    Integer findMaxBoardNo();

}
