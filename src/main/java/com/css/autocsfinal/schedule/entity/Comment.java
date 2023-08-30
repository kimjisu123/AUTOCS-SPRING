package com.css.autocsfinal.schedule.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="TBL_COMMENT")
@SequenceGenerator(
        name = "COMMENT_NO",
        sequenceName = "SEQ_COMMENT_NO",
        initialValue = 1, allocationSize = 1
)
@Getter
@Setter
@ToString
public class Comment {

    @Id
    @Column(name="COMMENT_CODE")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "COMMMENT_NO"
    )
    private int commentCode;

    @Column(name ="COMMENT_CONTENT")
    private String commentContent;

    @Column(name = "REF_COMMENT_CODE")
    private String refCommentCode;

    @Column(name = "EMPLOYEE_NO")
    private int employeeNo;

    @ManyToOne
    @JoinColumn(name="SCHEDULE_CODE")
    private Schedule schedule;


}
