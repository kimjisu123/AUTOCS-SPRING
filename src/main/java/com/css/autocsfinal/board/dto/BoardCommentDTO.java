package com.css.autocsfinal.board.dto;

import com.css.autocsfinal.member.dto.MemberDTO;
import lombok.*;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardCommentDTO {

    private int commentNo;

    private String commentContent;

    private Date regist;

    private Date modify;

    private char anonymity;

    private int refMemberNo;

    private int refBoardNo;

    //댓글에서 직원 직급과 부서와 이름 담을거
    private String employeeName;
    private String department;
    private String position;
    private String memberRole;

    //댓글에서 영업점
    private String storeName;

    //분리
    private MemberDTO member;
    private BoardDTO board;
}
