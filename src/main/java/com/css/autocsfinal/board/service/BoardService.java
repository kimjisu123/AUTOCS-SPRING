package com.css.autocsfinal.board.service;

import com.css.autocsfinal.board.dto.BoardDTO;
import com.css.autocsfinal.board.entity.Board;
import com.css.autocsfinal.board.repository.BoardRepository;
import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import com.css.autocsfinal.member.repository.EmployeeAndDepartmentAndPositionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    private final EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository;

    public BoardService(BoardRepository boardRepository, EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository) {
        this.boardRepository = boardRepository;
        this.employeeAndDepartmentAndPositionRepository = employeeAndDepartmentAndPositionRepository;
    }


    //모든 게시물 조회
        public List<BoardDTO> getAllBoard() {
            log.info("[BoardService] 모든 게시물 조회 Start ===================");

            List<Board> boardList = boardRepository.findAll();
            log.info("boardList : " + boardList);

            List<BoardDTO> BoardDTOList = boardList.stream()
                    .map(board -> {
                        BoardDTO boardDTO = new BoardDTO();

                        boardDTO.setBoardNo(board.getBoardNo());
                        boardDTO.setRefCategoryNo(board.getRefCategoryNo());
                        boardDTO.setRegist(board.getRegist());
                        boardDTO.setTitle(board.getTitle());
                        boardDTO.setContent(board.getContent());
                        boardDTO.setRefMemberNo(board.getRefMemberNo());

                        int memberNo = board.getRefMemberNo();

                        //멤버번호로 직원 조회
                        EmployeeAndDepartmentAndPosition employee = employeeAndDepartmentAndPositionRepository.findByMemberNo(memberNo);
                        if (employee != null) {
                            // 직원 정보가 존재하면 추가 정보 설정
                            boardDTO.setEmployeeName(employee.getName());
                            boardDTO.setDepartment(employee.getDepartment().getName());
                            boardDTO.setPosition(employee.getPosition().getName());
                        }

                        //이미지 나중에
                        //boardDTO.setFileUrl(IMAGE_URL + applyFormNApplyFile.getFile().getOrignal());

                        return boardDTO;
                    })
                    .collect(Collectors.toList());

            return BoardDTOList;
        }
    }

