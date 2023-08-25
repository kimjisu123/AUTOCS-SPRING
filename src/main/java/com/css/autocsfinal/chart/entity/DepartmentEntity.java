package com.css.autocsfinal.chart.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "TBL_DEPARTMENT")
public class DepartmentEntity {

    @Id
    @Column(name = "DEPARTMENT_CODE")
    private String departmentCode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "UPPER_DEPT_CODE")
    private String upperDeptCode;

    @Column(name = "ANOTHER_NAME")
    private String anotherName;

    // 자식 입장에서 부모를 바라봄 (하나의 부모는 여러개의 자식)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPPER_DEPT_CODE", insertable = false, updatable = false)
    private DepartmentEntity parent;

    @OneToMany(mappedBy = "parent")
    private List<DepartmentEntity> children = new ArrayList<>();

    @OneToMany(mappedBy = "departmentCode")
    private List<EmployeeAndPositionEntity> employee;
}
