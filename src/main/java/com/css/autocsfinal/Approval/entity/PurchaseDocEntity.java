package com.css.autocsfinal.Approval.entity;

import com.css.autocsfinal.member.entity.EmpPosiDeptEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TBL_DOCUMENT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PurchaseDocEntity {

    @Id
    @Column(name = "DOCUMENT_CODE")
    private int documentCode;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_NO", insertable = false, updatable = false)
    private EmpPosiDeptEntity employee;

    @Column(name = "APPLICATION_DATE")
    private Date applicationDate;

    @Column(name = "DOCUMENT_TYPE")
    private String documentType;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DOCUMENT_TITLE")
    private String documentTitle;

    @OneToMany(mappedBy = "documentCode")
    private List<AppEmpEntity> appEmp;

    @OneToMany
    @JoinColumn(name = "DOCUMENT_CODE")
    private List<RecEmpEntity> recEmp;
}
