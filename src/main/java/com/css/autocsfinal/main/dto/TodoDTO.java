package com.css.autocsfinal.main.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TodoDTO {

    private int todoNo;
    private String content;
    private char todoStatus;
    private Date regDate;
    private Date upDate;
    private int memberNo;

}
