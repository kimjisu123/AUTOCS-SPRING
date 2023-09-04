package com.css.autocsfinal.member.controller;

import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.mail.service.MailService;
import com.css.autocsfinal.market.service.EmailService;
import com.css.autocsfinal.member.dto.EmployeeAndDepartmentAndPositionDTO;
import com.css.autocsfinal.member.dto.EmployeeDTO;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    private final  EmailService emailService;

    public MemberController(MemberService memberService, MailService mailService, EmailService emailService) {
        this.memberService = memberService;
        this.emailService = emailService;
    }

    //사원 등록
    @Operation(summary = "사원 등록 요청", description = "사원을 등록합니다.", tags = {"MemberController"})
    @PostMapping("/insertEmployee")
    public ResponseEntity<ResponseDTO> registerEmployee(@RequestBody EmployeeDTO employeeDTO, MemberDTO memberDTO) {

        log.info("employeeDTO===========================> {}", employeeDTO);

        //사원 등록 전 아이디와 임시비밀번호발급
        String result1 = memberService.insertIdPwd(memberDTO);

        //사원 등록
        String resultMessage = memberService.insertEmployee(employeeDTO);

        HttpStatus httpStatus = (result1.contains("성공") && resultMessage.contains("성공")) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity
                .status(httpStatus)
                .body(new ResponseDTO(httpStatus, resultMessage, null));
    }

    //사원 조회 리스트
    @Operation(summary = "사원 조회 요청", description = "사원을 조회합니다.", tags = {"MemberController"})
    @GetMapping("/getEmployee")
    public ResponseEntity<ResponseDTO> getEmployee() {
        System.out.println("check ==========================");
        List<EmployeeAndDepartmentAndPositionDTO> employeeDTOList = memberService.getEmployee();

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "사원 조회 성공", employeeDTOList);

        return ResponseEntity.status(httpStatus).body(responseDTO);
    }

    //직원 아이디 찾기
    @GetMapping("/findEmployeeId")
    public ResponseEntity<ResponseDTO> findEmployeeId(@RequestParam String name, @RequestParam String employeeEmail) {
        try {
            // 이름과 이메일로 Employee 정보 조회
            EmployeeAndDepartmentAndPositionDTO foundEmployee = memberService.findEmployeeByNameAndEmail(name, employeeEmail);

            if (foundEmployee != null) {
                HttpStatus httpStatus = HttpStatus.OK;
                ResponseDTO responseDTO = new ResponseDTO(httpStatus, "아이디 찾기 성공", foundEmployee);
                return ResponseEntity.status(httpStatus).body(responseDTO);
            }

            // Employee 정보나 아이디가 없을 경우
            HttpStatus httpStatus = HttpStatus.NOT_FOUND;
            ResponseDTO responseDTO = new ResponseDTO(httpStatus, "사용자 정보 또는 아이디를 찾을 수 없습니다.", null);
            return ResponseEntity.status(httpStatus).body(responseDTO);
        } catch (Exception e) {
            log.error("Error while finding employee ID: {}", e.getMessage());
            HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            ResponseDTO responseDTO = new ResponseDTO(httpStatus, "아이디 찾기 중 오류가 발생했습니다.", null);
            return ResponseEntity.status(httpStatus).body(responseDTO);
        }
    }

    //비밀번호 인증번호 만들기
    @Operation(summary = "인증번호 생성", description = "인증번호를 생성합니다.", tags = {"MemberController"})
    private String generateVerificationCode() {
        // 영문 대문자, 소문자, 숫자로 이루어진 문자열
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";

        // 난수 생성기 초기화
        SecureRandom secureRandom = new SecureRandom();

        // 15자리의 인증번호 생성
        StringBuilder verificationCode = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            verificationCode.append(characters.charAt(randomIndex));
        }

        return verificationCode.toString();
    }

    //직원 비밀번호 찾기
    @Operation(summary = "직원 비밀번호 인증 요청", description = "인증번호 생성 후  이메일 발송합니다.", tags = {"MemberController"})
    @GetMapping("/findEmployeePwd")
    public ResponseEntity<ResponseDTO> findEmployeePwd(@RequestParam String name, @RequestParam String id, @RequestParam String employeeEmail) {
        try {
            // 이름과 이메일로 Employee 정보 조회
            EmployeeAndDepartmentAndPositionDTO foundEmployee = memberService.findEmployeeByNameAndEmail(name, employeeEmail);
            log.info("foundEmployee>>>>>>>>>>>>>>>>>>>>>>{}", foundEmployee);
            log.info("foundEmployee.id>>>>>>>>>>>>>>>>>>>>>>{}", foundEmployee.getMember().getId());

            if (foundEmployee != null && foundEmployee.getMember().getId().equals(id)) {

                // 인증번호 생성
                String verificationCode = generateVerificationCode();

                //인증번호 확인
                log.info("generateVerificationCode>>>>>>>>>>>>>>>>>>>>>>{}", verificationCode);

                // 인증번호 이메일 발송
                emailService.sendVerificationCode(employeeEmail, verificationCode);

                // 결과 배열 생성 및 값 담기
                String[] resultArray = new String[]{verificationCode, employeeEmail, id, name};

                HttpStatus httpStatus = HttpStatus.OK;
                ResponseDTO responseDTO = new ResponseDTO(httpStatus, "아이디 찾기 성공", resultArray);
                return ResponseEntity.status(httpStatus).body(responseDTO);
            }

            // Employee 정보나 아이디가 없을 경우
            HttpStatus httpStatus = HttpStatus.NOT_FOUND;
            ResponseDTO responseDTO = new ResponseDTO(httpStatus, "사용자 정보 또는 아이디를 찾을 수 없습니다.", null);
            return ResponseEntity.status(httpStatus).body(responseDTO);
        } catch (Exception e) {
            log.error("Error while finding employee ID: {}", e.getMessage());
            HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            ResponseDTO responseDTO = new ResponseDTO(httpStatus, "아이디 찾기 중 오류가 발생했습니다.", null);
            return ResponseEntity.status(httpStatus).body(responseDTO);
        }


    }

    // 직원 검색용 조회
    @GetMapping("/selectEmployee")
    public ResponseEntity<ResponseDTO> findbyAllEmployee(){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "모든 회원 조회 성공", memberService.findbyAllEmployee()));
    }

}
