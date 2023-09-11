package com.css.autocsfinal.chart.controller;

import com.css.autocsfinal.chart.dto.DeptResult;
import com.css.autocsfinal.chart.service.ChartService;
import com.css.autocsfinal.common.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chart")
@RequiredArgsConstructor
public class ChartController {

    private final ChartService chartService;

    @GetMapping()
    public ResponseEntity<ResponseDTO> findDept() {

        DeptResult deptResult = chartService.findDept();

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "조회 성공", deptResult);

        return ResponseEntity.ok().body(responseDTO);
    }


}
