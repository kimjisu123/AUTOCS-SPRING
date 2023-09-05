package com.css.autocsfinal.Approval.controller;

import com.css.autocsfinal.Approval.dto.*;
import com.css.autocsfinal.Approval.service.ApprovalService;
import com.css.autocsfinal.common.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/approval")
@RequiredArgsConstructor
@Slf4j
public class ApprovalController {

    private final ApprovalService approvalService;

    @GetMapping("/{employeeNo}")
    public ResponseEntity<?> appHome(@PathVariable int employeeNo) {

        log.info("[ApprovalController] appHome employeeNo : {} ", employeeNo);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", approvalService.findHomeList(employeeNo)));
    }


    /* 결재 라인 목록 불러오기 */
    @GetMapping("/appLine")
    public ResponseEntity<ResponseDTO> appLine() {

        List<AppDeptResult> deptList = approvalService.findDept();

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "조회 성공", deptList);

        return ResponseEntity.ok().body(responseDTO);
    }

    /* 구매 요청서 insert */
    @PostMapping(value = "/purchase")
    public ResponseEntity<?> purchase(@ModelAttribute PurchaseListDTO purchaseList,
                                        List<MultipartFile> files) {

        approvalService.registPurchase(purchaseList, files);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "insert 성공", null));
    }

    /* 여비 정산서 insert */
    @PostMapping(value = "/traffic")
    public ResponseEntity<?> traffic(@ModelAttribute TrafficListDTO trafficList, List<MultipartFile> files) {

        log.info("[ApprovalController] trafficList : {} ", trafficList);

        approvalService.registTraffic(trafficList, files);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "insert 성공", null));
    }

    /* 업무 보고서 insert */
    @PostMapping(value = "/business")
    public ResponseEntity<?> business(@ModelAttribute BusinessDTO business, List<MultipartFile> files) {

        log.info("[ApprovalController] businessContent : {} ", business );

        approvalService.registBusiness(business, files);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "insert 성공", null));
    }

    /* 휴가 불러오기 */
    @GetMapping("/getVacation/{employeeNo}")
    public ResponseEntity<?> getVacation(@PathVariable int employeeNo) {

        log.info("[ApprovalController] getVacation  empNo = {}", employeeNo);

        int vacation = approvalService.getVacation(employeeNo);

        log.info("[getVacation] vacation : {} ", vacation);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", vacation));
    }

    /* 휴가 테이블 insert */
    @GetMapping("/vacation")
    public ResponseEntity<?> vacation(@ModelAttribute VacationListDTO vacation, List<MultipartFile> files) {

        log.info("[ApprovalController] vacation");

        approvalService.registVacation(vacation, files);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "insert 성공", null));
    }

    /* 비용 청구 insert */
    @PostMapping("/pay")
    public ResponseEntity<?> pay(@ModelAttribute PayListDTO pay, List<MultipartFile> files) {

        log.info("[ApprovalController] pay : {} ", pay);

        approvalService.registPay(pay, files);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "insert 성공", null));
    }

    /* 발신 문서함 */
    @GetMapping("/send/{employeeNo}")
    public ResponseEntity<?> send(@PathVariable int employeeNo) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", approvalService.findSend(employeeNo)));
    }
}
