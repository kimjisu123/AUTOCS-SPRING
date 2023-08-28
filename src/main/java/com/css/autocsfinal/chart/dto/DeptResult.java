package com.css.autocsfinal.chart.dto;

import com.css.autocsfinal.chart.entity.DepartmentEntity;
import com.css.autocsfinal.chart.entity.EmployeeAndPositionEntity;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class DeptResult {

    private String id;
    private String name;
    private String title;
    private List<DeptResult> children;

    public DeptResult(DepartmentEntity departmentEntity) {

        this.id = departmentEntity.getDepartmentCode();
        this.name = departmentEntity.getName();
        this.title = departmentEntity.getAnotherName();

        /* 부서의 하위 부서를 DeptResult 객체로 매핑 */
        this.children = departmentEntity.getChildren().stream().map(DeptResult::new).collect(Collectors.toList());

        for(EmplAndPosiResult employee : departmentEntity.getEmployee().stream().map(EmplAndPosiResult::new).collect(Collectors.toList())) {

            System.out.println(employee.getTitle());
            this.children.add(new DeptResult(employee));
        }
    }

    public DeptResult(EmplAndPosiResult emplAndPosiResult) {

        this.id = emplAndPosiResult.getId();
        this.name = emplAndPosiResult.getName();
        this.title = emplAndPosiResult.getTitle();
    }
}
