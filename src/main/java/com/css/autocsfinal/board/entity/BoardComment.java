package com.css.autocsfinal.board.entity;

import com.css.autocsfinal.board.dto.BoardDTO;
import com.css.autocsfinal.member.dto.MemberDTO;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TBL_BOARD_COMMENT")
@SequenceGenerator(
        name = "BOARD_COMMENT_SEQ_GENERATOR",
        sequenceName = "SEQ_TBL_BOARD_COMMENT",
        initialValue = 1, allocationSize = 1
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardComment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BOARD_COMMENT_SEQ_GENERATOR"
    )
    @Column(name = "COMMENT_NO")
    private int commentNo;

    @Column(name = "COMMENT_CONTENT")
    private String commentContent;

    @Column(name = "REGIST_DATE")
    private Date regist;

    @Column(name = "MODIFY_DATE")
    private Date modify;

    @Column(name = "ANONYMITY")
    private char anonymity;

    @Column(name = "REF_MEMBER_NO")
    private int refMemberNo;

    @Column(name = "REF_BOARD_NO")
    private int refBoardNo;


}
