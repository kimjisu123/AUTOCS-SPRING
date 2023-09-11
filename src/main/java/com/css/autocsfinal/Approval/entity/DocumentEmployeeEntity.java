package com.css.autocsfinal.Approval.entity;

import com.css.autocsfinal.member.entity.Employee;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_DOCUMENT")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class DocumentEmployeeEntity {

    @Id
    @Column(name = "DOCUMENT_CODE")
    private int code;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_NO")
    private Employee employee;

    @Column(name = "APPLICATION_DATE")
    private Date applicationDate;

    @Column(name = "DOCUMENT_TYPE")
    private String documentType;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DOCUMENT_TITLE")
    private String documentTitle;
}
