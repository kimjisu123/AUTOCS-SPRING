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


    //분리
    private MemberDTO member;
    private BoardDTO board;
}
