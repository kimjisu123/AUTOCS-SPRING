package com.css.autocsfinal.main.dto;

import com.css.autocsfinal.main.entity.Todo;
import com.css.autocsfinal.member.entity.Member;
import lombok.*;

import java.time.LocalDate;
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

    private String url;


    public TodoDTO(Todo todo, String serviceUrl, Member member) {
        this.todoNo = todo.getTodoNo();
        this.content = todo.getContent();
        this.todoStatus = todo.getTodoStatus();
        this.memberNo = member.getNo();
        this.url = serviceUrl +this.todoNo;
    }
}
