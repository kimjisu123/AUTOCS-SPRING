package com.css.autocsfinal.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class RequestBodyTypeDTO {

    private EmployeeAndDepartmentAndPositionDTO employee;
    private MemberDTO member;
}
