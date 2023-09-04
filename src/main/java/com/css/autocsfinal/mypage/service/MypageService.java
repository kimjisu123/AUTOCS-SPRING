package com.css.autocsfinal.mypage.service;


import com.css.autocsfinal.member.dto.EmployeeAndDepartmentAndPositionDTO;
import com.css.autocsfinal.member.repository.EmployeeAndDepartmentAndPositionRepository;
import com.css.autocsfinal.member.repository.EmployeeRepository;
import com.css.autocsfinal.member.repository.MemberRepository;
import com.css.autocsfinal.mypage.dto.MemberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO;
import com.css.autocsfinal.mypage.dto.MemberFileDTO;
import com.css.autocsfinal.mypage.entity.MemberAndEmployeeAndDepartmentAndPositionAndMemberFile;
import com.css.autocsfinal.mypage.entity.MemberFile;
import com.css.autocsfinal.mypage.repository.MemberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository;
import com.css.autocsfinal.mypage.repository.MemberFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MypageService {

    private final MemberRepository memberRepository;
    public final MemberFileRepository memberFileRepository;
    public final MemberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository memberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository;

    public final ModelMapper modelMapper;
    public MypageService(MemberFileRepository memberFileRepository, EmployeeRepository employeeRepository, MemberRepository memberRepository, MemberRepository memberRepository1, EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository, MemberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository memberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository, ModelMapper modelMapper) {
        this.memberFileRepository = memberFileRepository;
        this.memberRepository = memberRepository;
        this.memberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository = memberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository;
        this.modelMapper = modelMapper;
    }

//    // 정보 조회하기
//    public List<MemberFileDTO> getMemberFile() {
//
//        log.info("[MypageService] findMember Start ==================================");
//        List<MemberFile> memberFile = memberFileRepository.findAll();
//        List<MemberFileDTO> memberFileDTOList = memberFile.stream()
//                .map(file -> {
//                    MemberFileDTO memberFileDTO = new MemberFileDTO();
//                    memberFileDTO.setFileNo(file.getFileNo());
//                    memberFileDTO.setOriginName(file.getOriginName());
//                    memberFileDTO.setMemberNo(file.getMember().getNo());
//                    memberFileDTO.setRegDate(new Date(System.currentTimeMillis()));
//                    memberFileDTO.setEmployeeJoin(file.getEmployeeAndDepartmentAndPosition().getEmployeeJoin());
//                    memberFileDTO.setEmployeeEmail(file.getEmployeeAndDepartmentAndPosition().getEmployeeEmail());
//                    memberFileDTO.setEmployeePhone(file.getEmployeeAndDepartmentAndPosition().getEmployeePhone());
//                    memberFileDTO.setEmployeeName(file.getEmployeeAndDepartmentAndPosition().getName());
//                    memberFileDTO.setPosition(file.getPosition());
//
//                    return memberFileDTO;
//                })
//                .collect(Collectors.toList());
//
//        if( employee != null ) {
//            EmployeeAndDepartmentAndPositionDTO employeeDTO = modelMapper.map(employee, EmployeeAndDepartmentAndPositionDTO.class);
//            log.info("[AuthService] member의 employee 조회 {} ================== ", employee);
//            return memberFileDTOList;
//
//    }



    //사원조회
    public List<MemberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO> getEmployeeFile() {
        log.info("[MemberFileService] 사원 조회 Start ===================");

        List<MemberAndEmployeeAndDepartmentAndPositionAndMemberFile> empinfoList = memberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository.findAll();
        log.info("empinfoList : " + empinfoList);

        // Employee 엔티티 리스트를 EmployeeDTO 리스트로 변환하여 반환
        List<MemberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO> employeeDTOList = empinfoList.stream()
                .map(memberAndEmployeeAndDepartmentAndPositionAndMemberFile -> {
                    MemberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO= new MemberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO();

                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setEmployeeNo(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getEmployeeNo());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setName(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getName());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setEmployeeJoin(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getEmployeeJoin());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setDepartment(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getDepartment().getName());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setPosition(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getPosition().getName());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setEmployeeEmail(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getEmployeeEmail());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setMemberNo(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getMember().getNo());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setFileNo(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getMemberFile().getFileNo());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setOriginName(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getMemberFile().getOriginName());


                    return memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO;
                })
                .collect(Collectors.toList());

        return employeeDTOList;
    }




//
//    @Transactional
//    public String updateImg(MemberFileDTO memberFileDTO) {
//
//        int fileNo = memberFileDTO.getFileNo();
//        MemberFile memberFile = memberFileRepository.findByMemberNo(fileNo);
//
//        int result = 0;
//
//        if(todo.getContent() != null){
//            todo.setContent(todoDTO.getContent());
//            todo.setUpDate(LocalDate.now());
//            log.info("[TodotService] updateTodo todo.getContent : {} " , todo.getContent());
//            log.info("[TodotService] updateTodo todo.setUpDate : {} " , todo.getUpDate());
//            result =1;
//        } else {
//            log.info("[TodotService] updateTodo 실패 ");
//            new RuntimeException();
//        }
//
//        Todo updateTodo = modelMapper.map(todoDTO,Todo.class);
//        return (result > 0)? " todo 수정 성공" : "todo 수정 실패";
//

//    }



}
