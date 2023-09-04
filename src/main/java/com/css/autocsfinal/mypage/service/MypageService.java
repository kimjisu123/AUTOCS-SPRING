package com.css.autocsfinal.mypage.service;


import com.css.autocsfinal.main.entity.Todo;
import com.css.autocsfinal.member.dto.EmployeeAndDepartmentAndPositionDTO;
import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import com.css.autocsfinal.member.repository.EmployeeAndDepartmentAndPositionRepository;
import com.css.autocsfinal.member.repository.EmployeeRepository;
import com.css.autocsfinal.member.repository.MemberRepository;
import com.css.autocsfinal.mypage.dto.MemberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO;
import com.css.autocsfinal.mypage.entity.MemberAndEmployeeAndDepartmentAndPositionAndMemberFile;
import com.css.autocsfinal.mypage.repository.MemberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository;
import com.css.autocsfinal.mypage.repository.MemberFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MypageService {

    private final MemberRepository memberRepository;
    public final MemberFileRepository memberFileRepository;
    public final MemberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository memberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository;

    public final EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository;
    public final ModelMapper modelMapper;
    public MypageService(MemberFileRepository memberFileRepository, EmployeeRepository employeeRepository, MemberRepository memberRepository, MemberRepository memberRepository1, EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository, MemberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository memberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository, EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository1, ModelMapper modelMapper) {
        this.memberFileRepository = memberFileRepository;
        this.memberRepository = memberRepository;
        this.memberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository = memberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository;
        this.employeeAndDepartmentAndPositionRepository = employeeAndDepartmentAndPositionRepository1;
        this.modelMapper = modelMapper;
    }




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

    @Transactional
    public String updateMemberInfo(EmployeeAndDepartmentAndPositionDTO employeeAndDepartmentAndPositionDTO) {

            int memberNo = employeeAndDepartmentAndPositionDTO.getMemberNo();
//            int memberNo = 41;
            EmployeeAndDepartmentAndPosition employeeAndDepartmentAndPosition = employeeAndDepartmentAndPositionRepository.findByMemberNo(memberNo);
            log.info("[MypageService] updateMemberInfo Start ======================================");
            log.info("[MypageService] updateMemberInfo memberNo : {} " , memberNo);
            int result = 0;

            if(employeeAndDepartmentAndPosition != null){
                employeeAndDepartmentAndPosition.setEmployeePhone(employeeAndDepartmentAndPositionDTO.getEmployeePhone());
                employeeAndDepartmentAndPosition.setEmployeeEmail(employeeAndDepartmentAndPositionDTO.getEmployeeEmail());
                employeeAndDepartmentAndPosition.setEmployeeNo(employeeAndDepartmentAndPositionDTO.getEmployeeNo());

                result =1;
            } else {
                log.info("[MypageService] updateMemberInfo 실패 ");
                new RuntimeException();
            }
        return (result > 0)? " 회원정보 수정 성공" : "회원정보 수정 실패";

        }
    }







