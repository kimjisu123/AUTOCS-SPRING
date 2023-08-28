package com.css.autocsfinal.market.controller;

import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.market.dto.ApplyFormDTO;
import com.css.autocsfinal.market.service.MarketService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/market")
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @Operation(summary = "영업점 신청폼 등록 요청", description = "신청폼을 등록합니다.", tags = {"MarketController"})
    @PostMapping("/applyMarket")
    public ResponseEntity<ResponseDTO> applyMarket(@RequestBody ApplyFormDTO applyFormDTO, List<MultipartFile> paramFileList) {

        // 영업점 신청폼 등록
        String resultMessage = marketService.insertApply(applyFormDTO, paramFileList);

        HttpStatus httpStatus = (resultMessage.contains("성공")) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity
                .status(httpStatus)
                .body(new ResponseDTO(httpStatus, resultMessage, null));
    }
}