package com.css.autocsfinal.board.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TBL_BOARD_FILE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FileAndBoard {

    @Id
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

    @ManyToOne()
    @JoinColumn(name = "REF_BOARD_NO")
    private Board board;

}
