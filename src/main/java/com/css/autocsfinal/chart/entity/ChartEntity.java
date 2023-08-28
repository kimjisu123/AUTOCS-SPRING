package com.css.autocsfinal.chart.entity;

import com.css.autocsfinal.member.entity.Department;
import com.css.autocsfinal.member.entity.Position;
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
public class ChartEntity {

    @Id
    @Column(name = "EMPLOYEE_NO")
    private int employeeNo;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "REF_DEPARTMENT_CODE")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "REF_POSITION_CODE")
    private Position position;

    @Column(name = "MANAGER_NO")
    private int managerNo;
}
