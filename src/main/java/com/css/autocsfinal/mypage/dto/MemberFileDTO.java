package com.css.autocsfinal.mypage.dto;

import com.css.autocsfinal.member.entity.Department;
import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.entity.Position;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberFileDTO {

    private int memberFileNo;;
    private String originName;
    private String changeName;
    private Date regDate;
    private Member member;

    private int memberNo;
//    private String departmentCode;
//    private String positionCode;

//    private Department department;
//    private Position position;

//
//    private int employeeNo;
//    private String employeeName;
//    private Date employeeJoin;
//    private String employeeEmail;
//    private String employeePhone;
//    private int employeeManager;


}
