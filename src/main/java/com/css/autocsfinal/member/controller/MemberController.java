package com.css.autocsfinal.member.controller;

import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.member.dto.EmployeeDTO;
import com.css.autocsfinal.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ResponseDTO> registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
        String resultMessage = memberService.insertEmployee(employeeDTO);

        HttpStatus httpStatus = resultMessage.contains("성공") ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity
                .status(httpStatus)
                .body(new ResponseDTO(httpStatus, resultMessage, null));
    }
}
