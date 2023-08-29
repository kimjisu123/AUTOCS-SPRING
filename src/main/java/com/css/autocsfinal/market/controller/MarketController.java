package com.css.autocsfinal.market.controller;

import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.market.dto.ApplyFormDTO;
import com.css.autocsfinal.market.dto.ApplyFormNApplyFileDTO;
import com.css.autocsfinal.market.dto.StoreInfoDTO;
import com.css.autocsfinal.market.entity.StoreInfo;
import com.css.autocsfinal.market.service.MarketService;
import com.css.autocsfinal.member.dto.EmployeeAndDepartmentAndPositionDTO;
import com.css.autocsfinal.member.dto.EmployeeDTO;
import com.css.autocsfinal.member.dto.MemberDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/market")
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @Operation(summary = "영업점 신청폼 등록 요청", description = "신청폼을 등록합니다.", tags = {"MarketController"})
    @PostMapping(value = "/applyMarket")
    public ResponseEntity<ResponseDTO> applyMarket(@ModelAttribute ApplyFormDTO applyFormDTO, MultipartFile fileImage) {

        log.info("[MarketController] fileImage {} =======> " + fileImage);

        // 영업점 신청폼 등록
        String resultMessage = marketService.insertApply(applyFormDTO, fileImage);

        HttpStatus httpStatus = (resultMessage.contains("성공")) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity
                .status(httpStatus)
                .body(new ResponseDTO(httpStatus, resultMessage, null));
    }

    //영업점 신청 대기 중인 목록 조회
    @Operation(summary = "영업점 계정 신청 대기 조회 요청", description = "영업점 계정 신청 대기 상태를 조회합니다.", tags = {"MarketController"})
    @GetMapping("/getMarketStateW")
    public ResponseEntity<ResponseDTO> getMarketStateW() {
        System.out.println("check ==========================");
        List<ApplyFormNApplyFileDTO> formNfileDTOList = marketService.getMarketStateW();

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "계정 신청 대기 중인 영업점 조회 성공", formNfileDTOList);

        return ResponseEntity.status(httpStatus).body(responseDTO);
    }

    //영업점 등록
    @Operation(summary = "영업점 계정생성 요청", description = "계정생성이 진행됩니다.", tags = {"MarketController"})
    @PostMapping("/insertMarket")
    public ResponseEntity<ResponseDTO> insertMarket(@RequestBody StoreInfoDTO storeInfoDTO, MemberDTO memberDTO) {

        log.info("StoreInfoDTO===========================> {}", storeInfoDTO);

        //영업점 등록 전 아이디와 임시비밀번호발급
        String result1 = marketService.insertIdPwd(memberDTO, storeInfoDTO);

        //영업점 등록
        String resultMessage = marketService.insertMarket(storeInfoDTO);

        HttpStatus httpStatus = (result1.contains("성공") && resultMessage.contains("성공")) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity
                .status(httpStatus)
                .body(new ResponseDTO(httpStatus, resultMessage, null));
    }
}