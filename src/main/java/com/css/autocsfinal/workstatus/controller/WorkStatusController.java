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

    @GetMapping("/workStatus")
    public ResponseEntity<ResponseDTO> findByAll(){

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "근태 관리 조회 성공",  workStatusService.selectReviewDetail()));
    }

    @GetMapping("/department")
    public ResponseEntity<ResponseDTO> findByDepartmentAll(){

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "본사 근태 관리 조회 성공", workStatusService.findByDepartmentAll()));
    }

    // 인사부 조회
    @GetMapping("/personnel")
    public ResponseEntity<ResponseDTO> findByPersonnel(){

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "인사부 조회 성공", workStatusService.findByPersonnel()));
    }

    // 재무/회계부 조회
    @GetMapping("/accounting")
    public ResponseEntity<ResponseDTO> findByAccounting(){

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "재무/회계부 조회 성공", workStatusService.findByAccounting()));
    }

    // 경영부 조회
    @GetMapping("/management")
    public ResponseEntity<ResponseDTO> findByManagement(){

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "경영부 조회 성공", workStatusService.findByManagement()));
    }

    // 마케팅부 조회
    @GetMapping("/marketing")
    public ResponseEntity<ResponseDTO> findByMarketing(){

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "마케팅부 조회 성공", workStatusService.findByMarketing()));
    }

    // 영업부 조회
    @GetMapping("/sales")
    public ResponseEntity<ResponseDTO> findBySales(){

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "영업부 조회 성공", workStatusService.findBySales()));
    }

    // 서비스부 조회
    @GetMapping("/service")
    public ResponseEntity<ResponseDTO> findByService(){

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "서비스부 조회 성공", workStatusService.findByService()));
    }

}
