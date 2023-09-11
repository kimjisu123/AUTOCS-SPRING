package com.css.autocsfinal.member.entity;

import com.css.autocsfinal.chart.entity.DepartmentEntity;
import com.css.autocsfinal.chart.entity.PositionEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class EmpPosiDeptEntity {

    @Id
    @Column(name = "EMPLOYEE_NO")
    private int employeeNo;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ANNUAL")
    private int annual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_POSITION_CODE")
    private PositionEntity position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_DEPARTMENT_CODE")
    private DepartmentEntity department;
}
