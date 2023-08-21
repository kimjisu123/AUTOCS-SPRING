package com.css.autocsfinal.member.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {

    private int no;
    private String name;
    private Date join;
    private Date out;
    private String email;
    private String phone;
    private int managerNo;
    private String DepartmentCode;
    private String PositionCode;
    private int MemberNo;
}
