package com.css.autocsfinal.mypage.service;


import com.css.autocsfinal.member.dto.EmployeeAndDepartmentAndPositionDTO;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.entity.Employee;
import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.EmployeeAndDepartmentAndPositionRepository;
import com.css.autocsfinal.member.repository.EmployeeRepository;
import com.css.autocsfinal.member.repository.MemberRepository;
import com.css.autocsfinal.mypage.repository.MemberFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MypageService {

    private final MemberRepository memberRepository;
    public final MemberFileRepository memberFileRepository;
    public final EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository;

    public final ModelMapper modelMapper;
    public MypageService(MemberFileRepository memberFileRepository, EmployeeRepository employeeRepository, MemberRepository memberRepository, MemberRepository memberRepository1, EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository, ModelMapper modelMapper) {
        this.memberFileRepository = memberFileRepository;
        this.memberRepository = memberRepository1;
        this.employeeAndDepartmentAndPositionRepository = employeeAndDepartmentAndPositionRepository;
        this.modelMapper = modelMapper;
    }

    // 한 회원조회하기
    public EmployeeAndDepartmentAndPositionDTO findMemberByInfo(int memberNo) {

        log.info("[MypageService] findMember Start ==================================");
        log.info("[MypageService] {} ================== ", memberNo);

//        Member member = memberRepository.findById(memberDTO.getId());

        EmployeeAndDepartmentAndPosition employee = employeeAndDepartmentAndPositionRepository.findByMemberNo(memberNo);
        if( employee != null ) {
            EmployeeAndDepartmentAndPositionDTO employeeDTO = modelMapper.map(employee, EmployeeAndDepartmentAndPositionDTO.class);
            log.info("[AuthService] member의 employee 조회 {} ================== ", employee);
            return employeeDTO;

        }

            return null;
//        EmployeeAndDepartmentAndPositionDTO employeeDTO = new EmployeeAndDepartmentAndPositionDTO();
//        employeeDTO.setEmployeeNo(employee.getEmployeeNo());
//        employeeDTO.setName(employee.getName());
//        employeeDTO.setEmployeeJoin(employee.getEmployeeJoin());
//        employeeDTO.setEmployeeEmail(employee.getEmployeeEmail());
//        employeeDTO.setEmployeePhone(employee.getEmployeePhone());
//        employeeDTO.setDepartment(employee.getDepartment().getName());
//        employeeDTO.setPosition(employee.getPosition().getName());





    }
}
