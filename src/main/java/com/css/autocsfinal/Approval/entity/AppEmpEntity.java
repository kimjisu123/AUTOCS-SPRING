package com.css.autocsfinal.Approval.entity;

import com.css.autocsfinal.member.dto.EmpPosiDeptDTO;
import com.css.autocsfinal.member.entity.EmpPosiDeptEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "TBL_APPROVAL")
public class AppEmpEntity {

    @Id
    @Column(name = "APPROVAL_CODE")
    private int approvalCode;

    @Column(name = "DOCUMENT_CODE")
    private int documentCode;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CMT")
    private String cmt;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_NO")
    private EmpPosiDeptEntity employee;
}
