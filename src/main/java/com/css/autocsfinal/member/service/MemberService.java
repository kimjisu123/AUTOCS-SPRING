package com.css.autocsfinal.member.service;

import com.css.autocsfinal.jwt.TokenProvider;
import com.css.autocsfinal.member.dto.EmployeeDTO;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.entity.Employee;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.EmployeeAndDepartmentAndPositionRepository;
import com.css.autocsfinal.member.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.css.autocsfinal.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MemberService {
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final EmployeeRepository employeeRepository;

    private final MemberRepository memberRepository;

    private final EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository;

    public MemberService(ModelMapper modelMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, EmployeeRepository employeeRepository, MemberRepository memberRepository, EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.employeeRepository = employeeRepository;
        this.memberRepository = memberRepository;
        this.employeeAndDepartmentAndPositionRepository = employeeAndDepartmentAndPositionRepository;
    }

    @Transactional
    public String insertIdPwd(MemberDTO memberDTO) {
        log.info("[MemberService] 아이디 비밀번호 발급 Start ===================");
        log.info("[AuthService] memberDTO {} =======> " + memberDTO);

        /* check
         * 값을 받은건 MemberDTO클래스이다. 여기 담긴 값을 repository를 통해서 쿼리를 요청해야한다.
         * 그래서 현재 MemberDTO객체를 entity 객체인 Member로 변경해주는 작업 필요하다. */
        Member registMember = modelMapper.map(memberDTO, Member.class);

        // 아이디 생성
        int nextMemberCode = memberRepository.maxMemberCode() + 1;
        String newUserId = "Member" + nextMemberCode;

        // 랜덤한 비밀번호 생성
        String newPassword = generateRandomPassword();

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(newPassword);

        registMember.setId(newUserId);
        registMember.setPwd(encodedPassword);
        registMember.setState("Y");
        registMember.setRole("본사직원");

        Member result = memberRepository.save(registMember);

        log.info("[AuthService] MemberInsert Result {}", (result != null) ? "계정 생성 성공" : "계정 생성 실패");

        return (result != null) ? "계정 생성 성공" : "계정 생성 실패";
    }

    private String generateRandomPassword() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    @Transactional
    public String insertEmployee(EmployeeDTO employeeDTO) {
        log.info("[MemberService] 사원 등록 Start ===================");
        log.info("[MemberService] employeeDTO : " + employeeDTO);

        Employee registEmployee = modelMapper.map(employeeDTO, Employee.class);

        Employee savedEmployee = employeeRepository.save(registEmployee);

        log.info("[MemberService] insertEmployee End ===================");
        return (savedEmployee != null) ? "사원 등록 성공" : "사원 등록 실패";
    }

    public List<EmployeeDTO> getEmployee() {
        log.info("[MemberService] 사원 조회 Start ===================");

        List<Employee> employeeList = employeeRepository.findAll();
        log.info("employeeList : " + employeeList);

        // Employee 엔티티 리스트를 EmployeeDTO 리스트로 변환하여 반환
        List<EmployeeDTO> employeeDTOList = employeeList.stream()
                .map(employee -> {
                    EmployeeDTO employeeDTO = new EmployeeDTO();

                    employeeDTO.setEmployeeNo(employee.getEmployeeNo());
                    employeeDTO.setName(employee.getName());
                    employeeDTO.setEmployeeJoin(employee.getEmployeeJoin());
                    employeeDTO.setDepartmentCode(employee.getDepartmentCode());
                    employeeDTO.setPositionCode(employee.getPositionCode());

                    return employeeDTO;
                })
                .collect(Collectors.toList());

        return employeeDTOList;
    }

}
