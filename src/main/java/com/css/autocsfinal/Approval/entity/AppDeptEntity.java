package com.css.autocsfinal.Approval.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_DEPARTMENT")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AppDeptEntity {

    @Id
    @Column(name = "DEPARTMENT_CODE")
    private String departmentCode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "UPPER_DEPT_CODE")
    private String upperDeptCode;
}
