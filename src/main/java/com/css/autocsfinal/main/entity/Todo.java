package com.css.autocsfinal.main.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_TODOLIST")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Todo {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TODOLIST_SEQ_GENERATOR"
    )
    @Column(name = "TODO_NO")
    private int todoNo;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "STATUS")
    private char todoStatus;
    @Column(name = "REG_DATE")
    private Date regDate;
    @Column(name = "REGIST_DATE")
    private Date upDate;
    @Column(name = "REF_MEMBER_NO")
    private int memberNo;
}
