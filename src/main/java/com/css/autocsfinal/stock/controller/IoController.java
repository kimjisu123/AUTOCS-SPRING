package com.css.autocsfinal.stock.controller;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.common.PageDTO;
import com.css.autocsfinal.common.PagingResponseDTO;
import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.stock.dto.IoDTO;
import com.css.autocsfinal.stock.dto.IoSummaryDTO;
import com.css.autocsfinal.stock.service.IoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class IoController {

    private final IoService ioService;

    public IoController(IoService ioService) {
        this.ioService = ioService;
    }

    /* 입출고 입력 */
    @PostMapping("/stock/stockio")
    public ResponseEntity<ResponseDTO> insertIo(@ModelAttribute IoDTO ioDTO){

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "입출고 입력 성공"
                        , ioService.insertIo(ioDTO)));
    }

    /* 입출고 조회 */
    @GetMapping("/stock/stockio")
    public ResponseEntity<ResponseDTO> selectIoListWithPaging(
            @RequestParam(name = "offset", defaultValue = "1") String offset){

        int total = ioService.selectIoAll();

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(ioService.selectIoListWithPaging(cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 입출고 조회 그룹화 - 이름검색, 페이징 필요 날짜 인자 받기*/
    @GetMapping("/stock/check")
    public ResponseEntity<List<IoSummaryDTO>> getIoSummary(
            @RequestParam Date startDate,
            @RequestParam Date endDate) {
        List<IoSummaryDTO> ioSummary = ioService.getsummarizeIoBetweenRegistDate();
        return ResponseEntity.ok(ioSummary);
    }



}
