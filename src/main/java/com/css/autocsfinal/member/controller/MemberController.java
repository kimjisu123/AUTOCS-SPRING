package com.css.autocsfinal.member.controller;

import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.member.dto.EmployeeAndDepartmentAndPositionDTO;
import com.css.autocsfinal.member.dto.EmployeeDTO;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.dto.PositionDTO;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.MemberRepository;
import com.css.autocsfinal.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
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




    // 사원 정보 수정

}
