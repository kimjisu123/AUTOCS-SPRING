package com.css.autocsfinal.board.entity;

import com.css.autocsfinal.board.dto.BoardDTO;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TBL_BOARD_FILE")
@SequenceGenerator(
        name = "BOARD_FILE_SEQ_GENERATOR",
        sequenceName = "SEQ_BOARD_FILE_NO",
        initialValue = 1, allocationSize = 1
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardFile {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BOARD_FILE_SEQ_GENERATOR"
    )
    @Column(name = "BOARD_FILE_NO")
    private int fileNo;

    @Column(name = "ORIGINAL")
    private String original;

    @Column(name = "CHANGE")
    private String change;

    @Column(name = "REGIST_DATE")
    private Date regist;

    @Column(name = "DROP_YN")
    private char dropYN;

    @Column(name = "REF_BOARD_NO")
    private int refBoardNo;

}
