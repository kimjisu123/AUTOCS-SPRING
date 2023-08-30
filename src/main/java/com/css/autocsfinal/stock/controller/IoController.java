package com.css.autocsfinal.stock.controller;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.common.PageDTO;
import com.css.autocsfinal.common.PagingResponseDTO;
import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.stock.dto.IoDTO;
import com.css.autocsfinal.stock.dto.IoSummaryDTO;
import com.css.autocsfinal.stock.service.IoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<ResponseDTO> summarizeWithPaging(
            @RequestParam(name = "offset", defaultValue = "1") String offset,
            @RequestParam(name = "s", defaultValue = "")String search,
            @RequestParam(name = "startDate", defaultValue = "")Date startDate,
            @RequestParam(name = "endDate", defaultValue = "")Date endDate){

        int total = ioService.summarizeSize(search, startDate, endDate);

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);


        List<Tuple> tuplePage = ioService.summarize(cri,search, startDate, endDate);

        List<IoSummaryDTO> ioSummaryPage = tuplePage.stream()
                .map(tuple -> {
                    int refProductNo = tuple.get(0, Integer.class);
                    long totalQuantityIn = tuple.get(1, Long.class);
                    long totalQuantityOut = tuple.get(2, Long.class);
                    String productName = tuple.get(3, String.class);
                    String categoryName = tuple.get(4, String.class);
                    String standardName = tuple.get(5, String.class);
                    String unitName = tuple.get(6, String.class);
                    int stock = tuple.get(7, Integer.class);
                    int price = tuple.get(8, Integer.class);
                    String etc = tuple.get(9, String.class);

                    return new IoSummaryDTO(refProductNo, totalQuantityIn, totalQuantityOut, productName, categoryName, standardName,
                            unitName, stock, price, etc );
                })
                .collect(Collectors.toList());


        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(ioSummaryPage);
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }




}
