package com.css.autocsfinal.market.controller;

import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.market.dto.ApplyFormAndApplyFileDTO;
import com.css.autocsfinal.market.service.MarketService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    //영업점 신청폼 insert
    @Operation(summary = "영업점 신청폼 등록 요청", description = "신청폼을 등록합니다.", tags = {"MarketController"})
    @PostMapping("/applyMarket")
    public ResponseEntity<ResponseDTO> registerEmployee(@RequestBody ApplyFormAndApplyFileDTO applyDTO) {

        //영업점 신청폼 등록
        String resultMessage = marketService.insertApply(applyDTO);

        HttpStatus httpStatus = (resultMessage.contains("성공")) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity
                .status(httpStatus)
                .body(new ResponseDTO(httpStatus, resultMessage, null));
    }
}
