package com.css.autocsfinal.Approval.entity;

import com.css.autocsfinal.Approval.dto.AppDeptResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TBL_EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AppEmplEntity {

    @Id
    @Column(name = "EMPLOYEE_NO")
    private int employeeNo;

    @Column(name = "NAME")
    private String name;

    @Column(name = "REF_DEPARTMENT_CODE")
    private String deptCode;

    @ManyToOne
    @JoinColumn(name = "REF_POSITION_CODE")
    private AppPositionEntity position;
}
