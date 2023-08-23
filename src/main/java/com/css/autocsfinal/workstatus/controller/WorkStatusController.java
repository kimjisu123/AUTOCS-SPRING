package com.css.autocsfinal.workstatus.controller;

import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.workstatus.dto.WorkStatusDTO;
import com.css.autocsfinal.workstatus.service.WorkStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class WorkStatusController {

    private final WorkStatusService workStatusService;

    @GetMapping("/management")
    public ResponseEntity<ResponseDTO> findByAll(){


        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "조회 성공",  workStatusService.selectReviewDetail()));
    }

}
