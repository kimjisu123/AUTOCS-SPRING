package com.css.autocsfinal.workstatus.controller;

import com.css.autocsfinal.workstatus.dto.WorkStatusDTO;
import com.css.autocsfinal.workstatus.service.WorkStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class WorkStatusController {

    private final WorkStatusService workStatusService;

    @GetMapping("/management")
    public ResponseEntity<WorkStatusDTO> findByAll(){

        return ResponseEntity.ok().body(new WorkStatusDTO(HttpStatus.OK, "조회 성공",  workStatusService.selectReviewDetail()));
    }

}
