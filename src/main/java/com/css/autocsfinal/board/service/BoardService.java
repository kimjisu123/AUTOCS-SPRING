package com.css.autocsfinal.board.service;

import com.css.autocsfinal.board.dto.BoardDTO;
import com.css.autocsfinal.board.dto.BoardFileDTO;
import com.css.autocsfinal.board.entity.Board;
import com.css.autocsfinal.board.entity.BoardFile;
import com.css.autocsfinal.board.repository.BoardFileRepository;
import com.css.autocsfinal.board.repository.BoardRepository;
import com.css.autocsfinal.market.dto.ApplyFormNApplyFileDTO;
import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import com.css.autocsfinal.member.repository.EmployeeAndDepartmentAndPositionRepository;
import com.css.autocsfinal.util.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    private final EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository;

    private final BoardFileRepository boardFileRepository;

    public BoardService(BoardRepository boardRepository, ModelMapper modelMapper, EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository, BoardFileRepository boardFileRepository) {
        this.boardRepository = boardRepository;
        this.modelMapper = modelMapper;
        this.employeeAndDepartmentAndPositionRepository = employeeAndDepartmentAndPositionRepository;
        this.boardFileRepository = boardFileRepository;
    }

    /* 이미지 저장 할 위치 및 응답 할 이미지 주소 */
    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;


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
                        boardDTO.setAnonymity(board.getAnonymity());

                        int memberNo = board.getRefMemberNo();

                        //멤버번호로 직원 조회
                        EmployeeAndDepartmentAndPosition employee = employeeAndDepartmentAndPositionRepository.findByMemberNo(memberNo);
                        if (employee != null) {
                            // 직원 정보가 존재하면 추가 정보 설정
                            boardDTO.setEmployeeName(employee.getName());
                            boardDTO.setDepartment(employee.getDepartment().getName());
                            boardDTO.setPosition(employee.getPosition().getName());
                        }

                        return boardDTO;
                    })
                    .collect(Collectors.toList());

            return BoardDTOList;
        }

    //게시물 등록(파일 있을 경우)
    @Transactional
    public String insertboardFile(BoardDTO boardDTO, MultipartFile[] fileImages) {
        log.info("[BoardService] 게시물 Insert Start ===================");
        log.info("[BoardService] boardDTO {} =======> " + boardDTO);
        log.info("[BoardService] fileImages {} =======> " + fileImages);

        Board insertBoard = modelMapper.map(boardDTO, Board.class);
        insertBoard.setRegist(Date.valueOf(LocalDate.now()));

        // ApplyFormAndApplyFile 객체 생성 및 매핑
        Board boardFrom = boardRepository.save(insertBoard);

        List<BoardFile> savedFiles = new ArrayList<>();
        try {
            for (MultipartFile fileImage : fileImages) {
                String imageName = UUID.randomUUID().toString().replace("-", "");
                String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, fileImage);

                log.info("[BoardService] insert Image Name : {}", replaceFileName);

                BoardFileDTO boardFileDTO = new BoardFileDTO();

                // ApplyFile 엔티티로 매핑하고 저장
                BoardFile insertFile = modelMapper.map(boardFileDTO, BoardFile.class);
                insertFile.setOriginal(replaceFileName);
                insertFile.setChange("게시물" + boardDTO.getRefMemberNo());
                insertFile.setRegist(Date.valueOf(LocalDate.now()));

                Integer maxBoardNo = boardRepository.findMaxBoardNo();
                insertFile.setRefBoardNo(maxBoardNo);
                insertFile.setDropYN('N');

                BoardFile savedFile = boardFileRepository.save(insertFile);
                savedFiles.add(savedFile);
            }

            log.info("[BoardService] 게시물 Insert End ===================");
            return (boardFrom != null && !savedFiles.isEmpty()) ? "게시물 등록 성공" : "게시물 등록 실패";
        } catch (Exception e) {
            log.error("Error while inserting apply form: {}", e.getMessage());
            for (BoardFile savedFile : savedFiles) {
                FileUploadUtils.deleteFile(IMAGE_DIR, savedFile.getOriginal());
            }
            throw new RuntimeException(e);
        }
    }

    //게시물 등록(파일 없을 경우)
    @Transactional
    public String insertboardNotFile(BoardDTO boardDTO) {
        log.info("[BoardService] 게시물 Insert Start ===================");
        log.info("[BoardService] boardDTO {} =======> " + boardDTO);

        try {
            Board insertBoard = modelMapper.map(boardDTO, Board.class);
            insertBoard.setRegist(Date.valueOf(LocalDate.now()));

            Board boardFrom = boardRepository.save(insertBoard);

            log.info("[BoardService] 게시물 Insert End ===================");
            return (boardFrom != null) ? "게시물 등록 성공" : "게시물 등록 실패";
        } catch (Exception e) {
            log.error("Error while inserting board: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //파일이 있을경우 파일과 게시물 조회
    public BoardDTO finBoard(int boardNo) {
        //게시물 조회
        Board board = boardRepository.findByBoardNo(boardNo);
        log.info("board : " + board);

        // BoardDTO에 정보를 담아서 반환
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardNo(boardNo);
        boardDTO.setTitle(board.getTitle());
        boardDTO.setRefCategoryNo(board.getRefCategoryNo());
        boardDTO.setRegist(board.getRegist());
        boardDTO.setContent(board.getContent());
        boardDTO.setAnonymity(board.getAnonymity());

        int memberNo = board.getRefMemberNo();
        //ref멤버번호로 직원 조회
        EmployeeAndDepartmentAndPosition employee = employeeAndDepartmentAndPositionRepository.findByMemberNo(memberNo);
        boardDTO.setEmployeeName(employee.getName());
        boardDTO.setDepartment(employee.getDepartment().getName());
        boardDTO.setPosition(employee.getPosition().getName());
        boardDTO.setRefMemberNo(memberNo);

        // 파일 조회
        int refBoardNo = boardNo;
        List<BoardFile> fileList = boardFileRepository.findByRefBoardNo(refBoardNo);
        List<String> fileUrls = new ArrayList<>();

        for (BoardFile file : fileList) {
            fileUrls.add(IMAGE_URL + file.getOriginal());
        }

        boardDTO.setFileUrls(fileUrls);

        return boardDTO;
    }

    //게시물 삭제
    @Transactional
    public String deleteBoard(int boardNo) {
        log.info("[BoardService] 게시물 Delete Start ===================");
        try {
            Board board = boardRepository.deleteByBoardNo(boardNo);
            int refBoardNo = boardNo;
            BoardFile boardFile = boardFileRepository.deleteByRefBoardNo(refBoardNo);
            log.info("[BoardService] 게시물 Delete End ===================");
            return (board == null && boardFile == null) ? "게시물 삭제 성공" : "게시물 삭제 실패";
        } catch (Exception e) {
            log.error("Error while deleting board: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

