package com.css.autocsfinal.Approval.controller;

import com.css.autocsfinal.Approval.dto.AppDeptResult;
import com.css.autocsfinal.Approval.entity.AppDeptEntity;
import com.css.autocsfinal.Approval.service.ApprovalService;
import com.css.autocsfinal.common.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/approval")
@RequiredArgsConstructor
public class ApprovalController {

    private final ApprovalService approvalService;

    @GetMapping("/appLine")
    public ResponseEntity<ResponseDTO> appLine() {

        List<AppDeptResult> deptList = approvalService.findDept();

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "조회 성공", deptList);

        return ResponseEntity.ok().body(responseDTO);
    }
}
