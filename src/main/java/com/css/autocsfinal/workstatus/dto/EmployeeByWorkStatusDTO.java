package com.css.autocsfinal.workstatus.dto;

import com.css.autocsfinal.member.entity.Department;
import com.css.autocsfinal.member.entity.Position;
import com.css.autocsfinal.workstatus.entity.WorkStatusList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeByWorkStatusDTO {

    private int employeeNo;

    private String name;

    private Date employeeJoin;

    private Date employeeOut;

    private String employeeEmail;

    private String employeePhone;

    private String upCode;

    private int memberNo;

    private Department department;

    private Position position;

    List<WorkStatusList> workStatusLists;

    public String toString() {
        return "Employee{" +
                "employeeNo=" + employeeNo +
                ", name='" + name + '\'' +
                ", employeeJoin=" + employeeJoin +
                ", employeeOut=" + employeeOut +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeePhone='" + employeePhone + '\'' +
                ", upCode=" + upCode +
                ", position=" + position +
                ", memberNo=" + memberNo +
                '}';
    }
}