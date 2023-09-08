package com.css.autocsfinal.board.dto;

import lombok.*;

import java.sql.Date;
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public class BoardFileDTO {

        private int fileNo;

        private String original;

        private String change;

        private Date regist;

        private char dropYN;

        private int refBoardNo;


        //분리
        private BoardDTO board;
    }

