package com.css.autocsfinal.board.service;

import com.css.autocsfinal.board.dto.BoardCommentDTO;
import com.css.autocsfinal.board.entity.BoardComment;
import com.css.autocsfinal.board.repository.CommentRepository;
import com.css.autocsfinal.market.entity.StoreInfo2;
import com.css.autocsfinal.market.repository.StoreInfo2Repository;
import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.EmployeeAndDepartmentAndPositionRepository;
import com.css.autocsfinal.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentService {

    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;
    private final EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository;
    private final StoreInfo2Repository storeInfo2Repository;
    private final MemberRepository memberRepository;

    public CommentService(ModelMapper modelMapper, CommentRepository commentRepository, EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository, StoreInfo2Repository storeInfo2Repository, MemberRepository memberRepository) {
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
        this.employeeAndDepartmentAndPositionRepository = employeeAndDepartmentAndPositionRepository;
        this.storeInfo2Repository = storeInfo2Repository;
        this.memberRepository = memberRepository;
    }

    //댓글 등록
    @Transactional
    public String insertBoardComment(BoardCommentDTO boardCommentDTO) {
        log.info("[CommentService] 댓글 Insert Start ===================");
        log.info("[CommentService] boardCommentDTO {} =======> " + boardCommentDTO);

        try {
            BoardComment insertComment = modelMapper.map(boardCommentDTO, BoardComment.class);
            insertComment.setRegist(Date.valueOf(LocalDate.now()));

            BoardComment commentFrom = commentRepository.save(insertComment);

            log.info("[CommentService] 댓글 Insert End ===================");
            return (commentFrom != null) ? "댓글 등록 성공" : "댓글 등록 실패";
        } catch (Exception e) {
            log.error("Error while inserting comment: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //모든 댓글 조회
    public List<BoardCommentDTO> getAllComment() {
        log.info("[CommentService] 모든 댓글 조회 Start ===================");

        List<BoardComment> commentList = commentRepository.findAll();
        log.info("commentList : " + commentList);

        List<BoardCommentDTO> CommentDTOList = commentList.stream()
                .map(comment -> {
                    BoardCommentDTO boardCommentDTO = new BoardCommentDTO();

                    boardCommentDTO.setCommentNo(comment.getCommentNo());
                    boardCommentDTO.setCommentContent(comment.getCommentContent());
                    boardCommentDTO.setRegist(comment.getRegist());
                    boardCommentDTO.setRefBoardNo(comment.getRefBoardNo());
                    boardCommentDTO.setAnonymity(comment.getAnonymity());
                    boardCommentDTO.setRefMemberNo(comment.getRefMemberNo());

                    int no = comment.getRefMemberNo();

                    //멤버 번호로 권한 담기
                    Member member = memberRepository.findByNo(no);
                    boardCommentDTO.setMemberRole(member.getRole());

                    int memberNo = comment.getRefMemberNo();

                    //멤버번호로 직원 조회
                    EmployeeAndDepartmentAndPosition employee = employeeAndDepartmentAndPositionRepository.findByMemberNo(memberNo);
                    if (employee != null) {
                        // 직원 정보가 존재하면 추가 정보 설정
                        boardCommentDTO.setEmployeeName(employee.getName());
                        boardCommentDTO.setDepartment(employee.getDepartment().getName());
                        boardCommentDTO.setPosition(employee.getPosition().getName());
                    }

                    //멤버번호로 영업점 조회
                    StoreInfo2 store = storeInfo2Repository.findByMemberNo(memberNo);
                    if (store != null) {
                        // 영업점 정보가 존재하면 추가 정보 설정
                        boardCommentDTO.setStoreName(store.getName());
                    }

                    return boardCommentDTO;
                })
                .collect(Collectors.toList());

        return CommentDTOList;
    }
}

