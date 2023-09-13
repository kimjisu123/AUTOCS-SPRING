package com.css.autocsfinal.board.controller;

import com.css.autocsfinal.board.dto.BoardDTO;
import com.css.autocsfinal.board.dto.BoardFileDTO;
import com.css.autocsfinal.board.entity.Board;
import com.css.autocsfinal.board.service.BoardService;
import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.market.dto.ApplyFormDTO;
import com.css.autocsfinal.market.dto.StoreInfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
@Tag(name = "Board", description = "게시판 API")
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

    //게시물 등록
    @Operation(summary = "게시물 등록 요청", description = "게시물을 등록합니다.", tags = {"BoardController"})
    @PostMapping(value = "/writingGo")
    public ResponseEntity<ResponseDTO> writingGo(@ModelAttribute BoardDTO boardDTO, MultipartFile[] fileImages) {

        log.info("[BoardDTO] boardDTO {} =======> " + boardDTO);
        log.info("[BoardDTO] fileImages {} =======> " + fileImages);

        // 파일이 있는 경우와 없는 경우를 분리
        if (fileImages != null) {
            // 파일이 존재하는 경우, 게시물 등록 및 파일 업로드 처리
            String resultMessage = boardService.insertboardFile(boardDTO, fileImages);

            HttpStatus httpStatus = (resultMessage.contains("성공")) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

            return ResponseEntity
                    .status(httpStatus)
                    .body(new ResponseDTO(httpStatus, resultMessage, null));
        } else {
            // 파일이 없는 경우, 파일을 업로드하지 않고 게시물만 등록
            String resultMessage = boardService.insertboardNotFile(boardDTO);

            HttpStatus httpStatus = (resultMessage.contains("성공")) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

            return ResponseEntity
                    .status(httpStatus)
                    .body(new ResponseDTO(httpStatus, resultMessage, null));
        }
    }

    //게시물 수정
    @Operation(summary = "게시물 등록 요청", description = "게시물을 등록합니다.", tags = {"BoardController"})
    @PostMapping(value = "/updateBoard")
    public ResponseEntity<ResponseDTO> updateBoard(@ModelAttribute BoardDTO boardDTO, MultipartFile[] fileImages) {

        log.info("[BoardDTO] boardDTO {} =======> " + boardDTO);
        log.info("[BoardDTO] fileImages {} =======> " + fileImages);

        // 파일이 있는 경우와 없는 경우를 분리
        if (fileImages != null) {
            // 파일이 존재하는 경우, 게시물 수정 및 파일 모두 지운 후 수정
            String resultMessage = boardService.updateBoardFile(boardDTO, fileImages);

            HttpStatus httpStatus = (resultMessage.contains("성공")) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

            return ResponseEntity
                    .status(httpStatus)
                    .body(new ResponseDTO(httpStatus, resultMessage, null));
        } else {
            // 파일이 없는 경우, 파일을 업로드하지 않고 게시물만 수정
            String resultMessage = boardService.updateBoardNotFile(boardDTO);

            HttpStatus httpStatus = (resultMessage.contains("성공")) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

            return ResponseEntity
                    .status(httpStatus)
                    .body(new ResponseDTO(httpStatus, resultMessage, null));
        }
    }

    //게시물 삭제
    @Operation(summary = "게시물 삭제 요청", description = "게시물을 삭제합니다.", tags = {"BoardController"})
    @PostMapping("/deleteBoard")
    public ResponseEntity<ResponseDTO> deleteBoard(@RequestParam int boardNo) {
        log.info("[BoardDTO] boardNo {} =======> " + boardNo);

        String board = boardService.deleteBoard(boardNo);

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "게시물 삭제 성공", board);

        return ResponseEntity.status(httpStatus).body(responseDTO);

    }

    //게시물 찾기
    @Operation(summary = "특정 게시물 찾기 요청", description = "특정 게시물을 찾습니다.", tags = {"BoardController"})
    @GetMapping("/getBoardNum")
    public ResponseEntity<ResponseDTO> getBoardNum(@RequestParam int boardNo) {
        log.info("[BoardDTO] boardNo {} =======> " + boardNo);

        BoardDTO board = boardService.finBoard(boardNo);

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "게시물 조회 성공", board);

        return ResponseEntity.status(httpStatus).body(responseDTO);

    }

    //게시물 찾기(내가 쓴 글)
    @Operation(summary = "내가 쓴 게시물 찾기 요청", description = "내가 쓴 게시물을 찾습니다.", tags = {"BoardController"})
    @GetMapping("/getMyBoarEmployee")
    public ResponseEntity<ResponseDTO> getMyBoarEmployee(@RequestParam int refMemberNo) {
        log.info("[BoardDTO] refMemberNo {} =======> " + refMemberNo);

        List<BoardDTO> board = boardService.findMyBoardEmployee(refMemberNo);

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "게시물 조회 성공", board);

        return ResponseEntity.status(httpStatus).body(responseDTO);

    }
}
