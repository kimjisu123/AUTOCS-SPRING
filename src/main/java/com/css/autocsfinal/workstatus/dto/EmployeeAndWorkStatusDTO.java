package com.css.autocsfinal.workstatus.dto;

import com.css.autocsfinal.member.dto.DepartmentDTO;
import com.css.autocsfinal.member.dto.PositionDTO;
import com.css.autocsfinal.member.entity.Department;
import com.css.autocsfinal.member.entity.Position;
import com.css.autocsfinal.workstatus.entity.WorkStatusList;
import com.css.autocsfinal.workstatus.entity.WorkStatusList2;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeAndWorkStatusDTO {


    private int employeeNo;
    private String name;
    private Date employeeJoin;
    private Date employeeOut;
    private String employeeEmail;
    private String employeePhone;
    private String upCode;
    private int memberNo;
    private DepartmentDTO department;
    private PositionDTO position;
    private List<WorkStatusListDTO> workStatusLists;


    @Override
    public String toString() {
        return "EmployeeAndWorkStatusDTO{" +
                "employeeNo=" + employeeNo +
                ", name='" + name + '\'' +
                ", employeeJoin=" + employeeJoin +
                ", employeeOut=" + employeeOut +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeePhone='" + employeePhone + '\'' +
                ", upCode='" + upCode + '\'' +
                ", memberNo=" + memberNo +
                ", department=" + department +
                ", position=" + position +
                ", workStatusLists=" + workStatusLists +
                '}';
    }
}