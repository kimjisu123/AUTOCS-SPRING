package com.css.autocsfinal.board.repository;

import com.css.autocsfinal.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository  extends JpaRepository<Board, Integer> {

    @Query(value = "SELECT MAX(boardNo) FROM Board")
    Integer findMaxBoardNo();

    //게시판 번호로 찾기
    Board findByBoardNo(int boardNo);

    //게시판 번호로 삭제
    int deleteByBoardNo(int boardNo);
}
