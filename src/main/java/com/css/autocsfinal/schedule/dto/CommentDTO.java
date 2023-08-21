package com.css.autocsfinal.schedule.entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private int commentCode;

    private String commentContent;

    private String refCommentCode;

    private int employeeNo;

    private int scheduleCode;

}
