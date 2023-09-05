package com.css.autocsfinal.member.service;

import com.css.autocsfinal.jwt.TokenProvider;
import com.css.autocsfinal.market.service.EmailService;
import com.css.autocsfinal.member.dto.EmployeeAndDepartmentAndPositionDTO;
import com.css.autocsfinal.member.dto.EmployeeDTO;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.entity.Employee;
import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.EmployeeAndDepartmentAndPositionRepository;
import com.css.autocsfinal.member.repository.EmployeeRepository;
import com.css.autocsfinal.member.repository.PositionRepository;
import com.css.autocsfinal.mypage.repository.MemberFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final PositionRepository positionRepository;

    private final EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository;

    private final EmailService emailService;

    private final MemberFileRepository memberFileRepository;

    @Autowired
    public MemberService(ModelMapper modelMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, EmployeeRepository employeeRepository, MemberRepository memberRepository, PositionRepository positionRepository, EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository, ,MemberFileRepository memberFileRepository,EmailService emailService) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.employeeRepository = employeeRepository;
        this.memberRepository = memberRepository;
        this.positionRepository = positionRepository;
        this.employeeAndDepartmentAndPositionRepository = employeeAndDepartmentAndPositionRepository;
        this.emailService = emailService;
        this.memberFileRepository = memberFileRepository;
    }

    @Transactional
    public String insertIdPwd(EmployeeDTO employeeDTO, MemberDTO memberDTO) {
        log.info("[MemberService] 아이디 비밀번호 발급 Start ===================");
        log.info("[AuthService] employeeDTO {} =======> " + employeeDTO);
        log.info("[AuthService] employeeEmail {} =======> " + employeeDTO.getEmployeeEmail());
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

        log.info("암호화 전 비밀번호 ============================>>>>>>>>>>>>>>>>>>>>>>>>>>> " + newPassword);

        // 아이디와 임시 비밀번호를 이메일로 전송
        String employeeEmail = employeeDTO.getEmployeeEmail();
        emailService.sendEmail2(employeeEmail, newUserId, newPassword);

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(newPassword);

        registMember.setId(newUserId);
        registMember.setPwd(encodedPassword);
        registMember.setState("Y");
        registMember.setRole("EMPLOYEE");

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

        // 최신 Member 코드 조회
        Integer maxMemberCode = memberRepository.maxMemberCode();

        // employeeDTO의 memberNo 설정
        employeeDTO.setMemberNo(maxMemberCode);

        // 직원의 연차 설정 (positionCode에 따라)
        String positionCode = employeeDTO.getPositionCode();
        int annual = calculateAnnual(positionCode);

        employeeDTO.setAnnual(annual);

        Employee registEmployee = modelMapper.map(employeeDTO, Employee.class);
        log.info("================> {}", registEmployee);

        Employee savedEmployee = employeeRepository.save(registEmployee);

        log.info("[MemberService] insertEmployee End ===================");
        return (savedEmployee != null) ? "사원 등록 성공" : "사원 등록 실패";
    }

    // positionCode에 따라 연차를 계산하는 메서드
    private int calculateAnnual(String positionCode) {
        int annual = 0;

        // positionCode에 따라 연차를 결정하는 규칙을 정의
        switch (positionCode) {
            case "i1":
                annual = 0;
                break;
            case "s1":
                annual = 12;
                break;
            default:
                annual = 15;
                break;
        }
        return annual;
    }

    //사원조회
    public List<EmployeeAndDepartmentAndPositionDTO> getEmployee() {
        log.info("[MemberService] 사원 조회 Start ===================");

        List<EmployeeAndDepartmentAndPosition> employeeList = employeeAndDepartmentAndPositionRepository.findAll();
        log.info("employeeList : " + employeeList);

        // Employee 엔티티 리스트를 EmployeeDTO 리스트로 변환하여 반환
        List<EmployeeAndDepartmentAndPositionDTO> employeeDTOList = employeeList.stream()
                .map(employeeAndDepartmentAndPosition -> {
                    EmployeeAndDepartmentAndPositionDTO employeeAndDepartmentAndPositionDTO = new EmployeeAndDepartmentAndPositionDTO();

                    employeeAndDepartmentAndPositionDTO.setEmployeeNo(employeeAndDepartmentAndPosition.getEmployeeNo());
                    employeeAndDepartmentAndPositionDTO.setName(employeeAndDepartmentAndPosition.getName());
                    employeeAndDepartmentAndPositionDTO.setEmployeeJoin(employeeAndDepartmentAndPosition.getEmployeeJoin());
                    employeeAndDepartmentAndPositionDTO.setEmployeeOut(employeeAndDepartmentAndPosition.getEmployeeOut());
                    employeeAndDepartmentAndPositionDTO.setDepartment(employeeAndDepartmentAndPosition.getDepartment().getName());
                    employeeAndDepartmentAndPositionDTO.setPosition(employeeAndDepartmentAndPosition.getPosition().getName());
                    employeeAndDepartmentAndPositionDTO.setReason(employeeAndDepartmentAndPosition.getReason());
                    employeeAndDepartmentAndPositionDTO.setReason(employeeAndDepartmentAndPosition.getReason());
                    employeeAndDepartmentAndPositionDTO.setMemberState(employeeAndDepartmentAndPosition.getMember().getState());

                    return employeeAndDepartmentAndPositionDTO;
                })
                .collect(Collectors.toList());

        return employeeDTOList;
    }

    //한명의 사원조회
    public EmployeeAndDepartmentAndPositionDTO findEmployeeId(int memberNo) {
        log.info("[MemberService] 아이디 찾기 Start ===================");

        EmployeeAndDepartmentAndPosition employeeList = employeeAndDepartmentAndPositionRepository.findByMemberNo(memberNo);
        log.info("employeeList : " + employeeList);

        // Employee 엔티티 리스트를 EmployeeDTO 리스트로 변환하여 반환
                    EmployeeAndDepartmentAndPositionDTO employeeAndDepartmentAndPositionDTO = new EmployeeAndDepartmentAndPositionDTO();

                    employeeAndDepartmentAndPositionDTO.setEmployeeNo(employeeList.getEmployeeNo());
                    employeeAndDepartmentAndPositionDTO.setName(employeeList.getName());
                    employeeAndDepartmentAndPositionDTO.setEmployeeJoin(employeeList.getEmployeeJoin());
                    employeeAndDepartmentAndPositionDTO.setDepartment(employeeList.getDepartment().getName());
                    employeeAndDepartmentAndPositionDTO.setPosition(employeeList.getPosition().getName());
                    employeeAndDepartmentAndPositionDTO.setEmployeeEmail(employeeList.getEmployeeEmail());
                    employeeAndDepartmentAndPositionDTO.setEmployeePhone(employeeList.getEmployeePhone());
                    employeeAndDepartmentAndPositionDTO.setMemberId(employeeList.getMember().getId());
                    employeeAndDepartmentAndPositionDTO.setPw(employeeList.getMember().getPwd());
                    employeeAndDepartmentAndPositionDTO.setMemberNo(employeeList.getMember().getNo());
//                    employeeAndDepartmentAndPositionDTO.setMemberFileName(employeeList.getMemberFile().getOriginName());

                    return employeeAndDepartmentAndPositionDTO;


    }

    // Employee 정보 조회(아이디 찾기)
    public EmployeeAndDepartmentAndPositionDTO findEmployeeByNameAndEmail(String name, String employeeEmail) {
        EmployeeAndDepartmentAndPosition employee = employeeAndDepartmentAndPositionRepository.findByNameAndEmployeeEmail(name, employeeEmail);
        if (employee != null) {
            EmployeeAndDepartmentAndPositionDTO employeeDTO = modelMapper.map(employee, EmployeeAndDepartmentAndPositionDTO.class);
            return employeeDTO;
        }
        return null;
    }

    //아이디로 멤버 조회
    public Member findMemberById(String Id) {
        return memberRepository.findById(Id);
    }

    //비밀번호 업데이트
    public void updateMember(Member member) {
        memberRepository.save(member);
    }

    public Object findbyAllEmployee() {

        List<EmployeeAndDepartmentAndPosition> employee = employeeAndDepartmentAndPositionRepository.findAll();

        log.info("=========================================================> {}", employee);
        if (employee != null) {
            List<EmployeeAndDepartmentAndPositionDTO> employeeDTO = employee.stream().map(Employee -> modelMapper.map(Employee, EmployeeAndDepartmentAndPositionDTO.class)).collect(Collectors.toList());
            return employeeDTO;
        }
        return null;
    }

    //아이디로 직원 찾기
    public EmployeeAndDepartmentAndPosition findEmployee(int memberNo) {
        return employeeAndDepartmentAndPositionRepository.findByMemberNo(memberNo);
    }

    //퇴사일과 사유 insert
    public void saveEmployee(EmployeeAndDepartmentAndPosition foundEmployee) {
        employeeAndDepartmentAndPositionRepository.save(foundEmployee);
    }

    //직원 번호로 전체 직원 찾기
    public EmployeeAndDepartmentAndPosition findEmployeeByNo(int employeeNo) {
        return employeeAndDepartmentAndPositionRepository.findByEmployeeNo(employeeNo);
    }
}

