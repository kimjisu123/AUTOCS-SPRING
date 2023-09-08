package com.css.autocsfinal.mypage.dto;

import com.css.autocsfinal.member.dto.MemberDTO;
import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO {


    private int memberNo;
    private Character state;
    private String role;
    private int employeeNo;
    private String name;
    private Date employeeJoin;
    private Date employeeOut;
    private String employeeEmail;
    private String employeePhone;
    private int employeeManager;
    private String departmentCode;
    private String positionCode;
    private int fileNo;
    private String originName;
    private String changeName;
    private Date regDate;

    //나중에 이것때문에 에러 날 가능성 있음
    //1. 다른 타입의 member 추가
//    private String member;
    private MemberDTO member;
    private String department;
    private String position;
    private String memberFile;

//    private String originName;




}
