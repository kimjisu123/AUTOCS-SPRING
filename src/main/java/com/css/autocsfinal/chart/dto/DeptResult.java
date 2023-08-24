package com.css.autocsfinal.chart.dto;

import com.css.autocsfinal.chart.entity.DepartmentEntity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
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
    }
}
