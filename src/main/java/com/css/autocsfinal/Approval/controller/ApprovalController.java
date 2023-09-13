package com.css.autocsfinal.Approval.controller;

import com.css.autocsfinal.Approval.dto.*;
import com.css.autocsfinal.Approval.entity.DocumentFileEntity;
import com.css.autocsfinal.Approval.service.ApprovalService;
import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.common.PageDTO;
import com.css.autocsfinal.common.PagingResponseDTO;
import com.css.autocsfinal.common.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/approval")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Approval", description = "전자결재 API")
public class ApprovalController {

    private final ApprovalService approvalService;

    @GetMapping("/{employeeNo}")
    @Operation(summary = "전자결재 홈 화면", description = "결재요청 문서와 완료 문서 3개씩 출력합니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> appHome(@PathVariable int employeeNo) {

        log.info("[ApprovalController] appHome employeeNo : {} ", employeeNo);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", approvalService.findHomeList(employeeNo)));
    }


    /* 결재 라인 목록 불러오기 */
    @GetMapping("/appLine")
    @Operation(summary = "결재 라인 목록", description = "결재 라인 목록을 불러옵니다.", tags = {"ApprovalController"})
    public ResponseEntity<ResponseDTO> appLine() {

        List<AppDeptResult> deptList = approvalService.findDept();

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "조회 성공", deptList);

        return ResponseEntity.ok().body(responseDTO);
    }

    /* 구매 요청서 insert */
    @PostMapping(value = "/purchase")
    @Operation(summary = "구매 요청서 결재 요청 API", description = "구매 요청서를 결재 요청합니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> purchase(@ModelAttribute PurchaseListDTO purchaseList,
                                        List<MultipartFile> files) {

        approvalService.registPurchase(purchaseList, files);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "insert 성공", null));
    }

    /* 여비 정산서 insert */
    @PostMapping(value = "/traffic")
    @Operation(summary = "여비 정산서 결재 요청 API", description = "여비 정산서를 결재 요청합니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> traffic(@ModelAttribute TrafficListDTO trafficList, List<MultipartFile> files) {

        log.info("[ApprovalController] trafficList : {} ", trafficList);

        approvalService.registTraffic(trafficList, files);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "insert 성공", null));
    }

    /* 업무 보고서 insert */
    @PostMapping(value = "/business")
    @Operation(summary = "업무 보고서 결재 요청 API", description = "업무 보고서를 결재 요청합니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> business(@ModelAttribute BusinessDTO business, List<MultipartFile> files) {

        log.info("[ApprovalController] businessContent : {} ", business );

        approvalService.registBusiness(business, files);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "insert 성공", null));
    }

    /* 휴가 불러오기 */
    @GetMapping("/getVacation/{employeeNo}")
    @Operation(summary = "잔여 휴가 API", description = "잔여 휴가를 조회합니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> getVacation(@PathVariable int employeeNo) {

        log.info("[ApprovalController] getVacation  empNo = {}", employeeNo);

        int vacation = approvalService.getVacation(employeeNo);

        log.info("[getVacation] vacation : {} ", vacation);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", vacation));
    }

    /* 휴가 테이블 insert */
    @PostMapping("/vacation")
    @Operation(summary = "휴가 신청서 결재 요청 API", description = "휴가 신청서를 결재 요청합니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> vacation(@ModelAttribute VacationListDTO vacation, List<MultipartFile> files) throws ParseException {

        log.info("[ApprovalController] vacation : {}", vacation);

        approvalService.registVacation(vacation, files);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "insert 성공", null));
    }

    /* 비용 청구 insert */
    @PostMapping("/pay")
    @Operation(summary = "비용 청구서 결재 요청 API", description = "비용 청구서를 결재 요청합니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> pay(@ModelAttribute PayListDTO pay, List<MultipartFile> files) {

        log.info("[ApprovalController] pay : {} ", pay);

        approvalService.registPay(pay, files);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "insert 성공", null));
    }

    /* 발신 문서함 */
    @GetMapping("/send/{employeeNo}")
    @Operation(summary = "발신 문서함", description = "발신 문서 리스트를 불러옵니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> send(@PathVariable int employeeNo, @RequestParam(name = "offset", defaultValue = "1") String offset) {

        int total = approvalService.selectTotal(employeeNo);

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO()
                ;
        /* 1. offset의 번호에 맞는 페이지에 뿌려줄 정보 */
        pagingResponseDTO.setData(approvalService.sendWithPaging(cri, employeeNo));
        /* 2. PageDTO : 화면에서 페이징 처리에 필요한 정보들 */
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 업무 문서함 */
    @GetMapping("/myBusiness/{employeeNo}")
    @Operation(summary = "업무 문서함", description = "업무 문서 리스트를 불러옵니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> myBusiness(@PathVariable int employeeNo, @RequestParam(name = "offset", defaultValue = "1") String offset) {

        int total = approvalService.selectMyBusiness(employeeNo);

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(approvalService.myBusinessWithPaging(cri, employeeNo));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 결재 대기 문서 */
    @GetMapping("/appWait/{employeeNo}")
    @Operation(summary = "결재 대기 문서함", description = "결재 대기 문서 리스트를 불러옵니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> appWait(@PathVariable int employeeNo, @RequestParam(name = "offset", defaultValue = "1") String offset) {

        int total = approvalService.selectAppWait(employeeNo);

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(approvalService.appWaitPaging(cri, employeeNo));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 참조/열람 대기 문서 */
    @GetMapping("/seeWait/{employeeNo}")
    @Operation(summary = "참조/열람 대기 문서함", description = "참조/열람 대기 문서 리스트를 불러옵니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> seeWait(@PathVariable int employeeNo, @RequestParam(name = "offset", defaultValue = "1") String offset) {

        int total = approvalService.selectSeeWait(employeeNo);

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(approvalService.seeWaitPaging(cri, employeeNo));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 결재 문서함 */
    @GetMapping("/myApp/{employeeNo}")
    @Operation(summary = "결재 문서함", description = "결재 요청 온 모든 문서 리스트를 불러옵니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> myApp(@PathVariable int employeeNo, @RequestParam(name = "offset", defaultValue = "1") String offset) {

        int total = approvalService.selectMyApp(employeeNo);

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(approvalService.myAppPaging(cri, employeeNo));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 참조/열람 문서함 */
    @GetMapping("/mySee/{employeeNo}")
    @Operation(summary = "참조/열람 문서함", description = "수신 요청 온 모든 문서 리스트를 불러옵니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> mySee(@PathVariable int employeeNo, @RequestParam(name = "offset", defaultValue = "1") String offset) {

        int total = approvalService.selectMySee(employeeNo);

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(approvalService.mySeePaging(cri, employeeNo));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 업무보고 문서 정보 */
    @GetMapping("/document/business/{documentCode}")
    @Operation(summary = "업무보고 문서", description = "선택한 업무보고 문서를 불러옵니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> businessDoc(@PathVariable int documentCode) {

        log.info("=========================================== {}", documentCode);

        BusinessDocDTO businessDoc = approvalService.getBusinessDoc(documentCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", businessDoc));
    }

    /* 여비정산 문서 정보 */
    @GetMapping("/document/traffic/{documentCode}")
    @Operation(summary = "여비정산 문서", description = "선택한 여비정산 문서를 불러옵니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> trafficDoc(@PathVariable int documentCode) {

        TrafficDocDTO trafficDoc = approvalService.getTrafficDoc(documentCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", trafficDoc));
    }

    /* 구매요청 문서 정보 */
    @GetMapping("/document/purchase/{documentCode}")
    @Operation(summary = "구매요청 문서", description = "선택한 구매요청 문서를 불러옵니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> purchaseDoc(@PathVariable int documentCode) {

        PurchaseDocDTO purchaseDoc = approvalService.getPurchaseDoc(documentCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", purchaseDoc));
    }

    /* 휴가신청 문서 정보 */
    @GetMapping("/document/vacation/{documentCode}")
    @Operation(summary = "휴가신청 문서", description = "선택한 휴가신청 문서를 불러옵니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> vacationDoc(@PathVariable int documentCode) {

        VacationDocDTO vacationDoc = approvalService.getVacationDoc(documentCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", vacationDoc));
    }

    /* 비용청구 문서 정보 */
    @GetMapping("/document/pay/{documentCode}")
    @Operation(summary = "비용청구 문서", description = "선택한 비용청구 문서를 불러옵니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> payDoc(@PathVariable int documentCode) {

        PayDocDTO payDoc = approvalService.getPayDoc(documentCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", payDoc));
    }

    /* 승인정보 불러오기 */
    @GetMapping("/document/appYN/{documentCode}")
    @Operation(summary = "승인정보", description = "승인 혹은 수신한 사람이 있는지 확인합니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> appYN(@PathVariable int documentCode) {

        boolean yn = approvalService.getAppYN(documentCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", yn));
    }

    /* 문서 삭제 */
    @DeleteMapping("/delete/{documentCode}")
    @Operation(summary = "문서삭제 API", description = "문서를 삭제합니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> deleteDocument(@PathVariable int documentCode) {

        approvalService.deleteDocument(documentCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "삭제 성공", null));
    }

    /* 승인 */
    @PutMapping("/{documentCode}/{employeeNo}")
    @Operation(summary = "승인 API", description = "요청 온 결재를 승인합니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> putApproval(@PathVariable int documentCode, @PathVariable int employeeNo) {

        log.info("controller =================================================");

        approvalService.putApproval(documentCode, employeeNo);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "승인 성공", null));
    }

    /* 반려 */
    @PutMapping("/back/{documentCode}/{employeeNo}")
    @Operation(summary = "반려 API", description = "요청 온 결재를 반려합니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> backDocument(@PathVariable int documentCode, @PathVariable int employeeNo, InputStream inputStream) throws IOException {

        String comment = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("[ApprovalController] =============================== comment {}", comment);

        approvalService.backDocument(comment, documentCode, employeeNo);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "반려 성공", null));
    }

    /* 휴가 사용 날짜*/
    @PutMapping("/putVacation/{documentCode}/{useDate}")
    @Operation(summary = "휴가 사용", description = "휴가신청이 승인되면 휴가를 차감시킵니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> putVacation(@PathVariable int documentCode, @PathVariable int useDate) {

        approvalService.putVacation(documentCode, useDate);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "휴가 사용", null));
    }

    /* 파일 다운로드*/
    @GetMapping("/download/{fileCode}")
    @Operation(summary = "파일 다운로드API", description = "파일을 클릭하면 다운로드를 합니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> downloadFile(@PathVariable int fileCode, HttpServletResponse response) throws IOException {

        log.info("============================================ fileCode : {}", fileCode);

        byte[] file = approvalService.downloadFile(fileCode);
        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("*"))
                .body(file);
    }

    /* 참조 확인 */
    @PutMapping("/putReceiver/{documentCode}/{employeeNo}")
    @Operation(summary = "참조 확인API", description = "수신 온 파일을 확인합니다.", tags = {"ApprovalController"})
    public ResponseEntity<?> putReceiver(@PathVariable int employeeNo, @PathVariable int documentCode) {

        approvalService.putReceiver(employeeNo, documentCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "확인", null));
    }

}
