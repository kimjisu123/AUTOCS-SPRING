package com.css.autocsfinal.chart.dto;

import com.css.autocsfinal.chart.entity.EmployeeAndPositionEntity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EmplAndPosiResult {

    private String id;
    private String name;
    private String title;

    public EmplAndPosiResult(EmployeeAndPositionEntity employeeAndPositionEntity) {

        this.id = employeeAndPositionEntity.getPosition().getPositionCode();
        this.name = employeeAndPositionEntity.getPosition().getName();
        this.title = employeeAndPositionEntity.getName();
    }
}
