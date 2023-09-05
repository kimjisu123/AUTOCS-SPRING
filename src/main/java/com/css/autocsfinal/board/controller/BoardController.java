package com.css.autocsfinal.board.controller;

import com.css.autocsfinal.board.dto.BoardDTO;
import com.css.autocsfinal.board.service.BoardService;
import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.market.dto.ApplyFormNApplyFileDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //모든 게시물 조회
    @Operation(summary = "모든 게시물 조회 요청", description = "모든 게시물을 조회합니다.", tags = {"BoardController"})
    @GetMapping("/getBoardAll")
    public ResponseEntity<ResponseDTO> getBoardAll() {
        System.out.println("check ==========================");
        List<BoardDTO> boardDTOList = boardService.getAllBoard();

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "모든 게시물 조회 성공", boardDTOList);

        return ResponseEntity.status(httpStatus).body(responseDTO);
    }
}
