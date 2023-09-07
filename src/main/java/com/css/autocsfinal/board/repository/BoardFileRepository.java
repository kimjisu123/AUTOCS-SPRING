package com.css.autocsfinal.board.repository;

import com.css.autocsfinal.board.dto.BoardFileDTO;
import com.css.autocsfinal.board.entity.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardFileRepository extends JpaRepository<BoardFile, Integer> {


    List<BoardFile> findByRefBoardNo(int refBoardNo);
}
