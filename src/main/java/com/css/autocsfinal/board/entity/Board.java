package com.css.autocsfinal.board.entity;

import com.css.autocsfinal.board.dto.BoardCategoryDTO;
import com.css.autocsfinal.member.dto.MemberDTO;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TBL_BOARD")
@SequenceGenerator(
        name = "BOARD_SEQ_GENERATOR",
        sequenceName = "SEQ_BOARD_NO",
        initialValue = 50, allocationSize = 1
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Board {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BOARD_SEQ_GENERATOR"
    )
    @Column(name = "BOARD_NO")
    private int boardNo;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "REGIST_DATE")
    private Date regist;

    @Column(name = "MODIFY_DATE")
    private Date modify;

    @Column(name = "DELETE_DATE")
    private Date delete;

    @Column(name = "REF_CATEGORY_NO")
    private int refCategoryNo;

    @Column(name = "REF_MEMBER_NO")
    private int refMemberNo;


}
