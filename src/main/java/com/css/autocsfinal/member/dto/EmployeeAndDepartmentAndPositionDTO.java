package com.css.autocsfinal.member.dto;

import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.mypage.dto.MemberFileDTO;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeAndDepartmentAndPositionDTO {

    private int employeeNo;
    private String name;
    private Date employeeJoin;
    private Date employeeOut;
    private String employeeEmail;
    private String employeePhone;
    private int employeeManager;
    private String reason;
    private int annual;

    //나중에 이것때문에 에러 날 가능성 있음
    //1. 다른 타입의 member 추가
//    private String member;
    private MemberDTO member;
    private String department;
    private String position;
    private String memberId;
    private String pw;
    private int memberNo;
    private String memberFile;

    private String memberState;

}
