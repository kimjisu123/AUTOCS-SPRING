package com.css.autocsfinal.stock.controller;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.common.PageDTO;
import com.css.autocsfinal.common.PagingResponseDTO;
import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.stock.dto.*;
import com.css.autocsfinal.stock.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@Slf4j
@Tag(name = " Bill", description = "세금계선서 API")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }


    /* 계산서 입력 */
    @Operation(summary = "세금계산서 입력 요청", description = "세금계산서 입력이 진행됩니다.", tags = { "BillController" })
    @PostMapping("/stock/myorderlist")
    public ResponseEntity<ResponseDTO> insertBill(@ModelAttribute BillDTO billDTO){

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "계산서 입력 성공"
                        , billService.insertBill(billDTO)));
    }



    /* 계산서 조회 페이징*/
    @Operation(summary = "세금계산서 전체조회 요청", description = "세금계산서 전체조회 진행됩니다.", tags = { "BillController" })
    @GetMapping("/stock/bill")
    public ResponseEntity<ResponseDTO> billListWithPaging(
            @RequestParam(name = "offset", defaultValue = "1") String offset,
            @RequestParam(name = "store", defaultValue = "")String store,
            @RequestParam(name = "startDate", defaultValue = "") Date startDate,
            @RequestParam(name = "endDate", defaultValue = "")Date endDate
    ){

        int total = billService.billListSize(store, startDate, endDate);

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        List<Tuple> tuplePage = billService.billList(cri, store, startDate, endDate);

        List<BillInfoDTO> billPage = tuplePage.stream()
                .map(tuple -> {
                    BigDecimal billNo =  tuple.get(0, BigDecimal.class);
                    BigDecimal orderNo =  tuple.get(1, BigDecimal.class);
                    String storeInfoName = tuple.get(2, String.class);
                    String registDate = tuple.get(3, String.class);

                    return new BillInfoDTO(billNo.intValue(), orderNo.intValue(), storeInfoName, registDate);
                })
                .collect(Collectors.toList());


        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(billPage);
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 계산서 조회 페이징*/
//    @GetMapping("/stock/bill")
//    public ResponseEntity<ResponseDTO> selectCategoryListWithPaging(
//            @RequestParam(name = "offset", defaultValue = "1") String offset){
//
//        int total = billService.selectBillAll();
//
//        Criteria cri = new Criteria(Integer.valueOf(offset), 10);
//
//        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
//        pagingResponseDTO.setData(billService.selectBillListWithPaging(cri));
//        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
//    }

    /* 주문물품조회 - 영업점별 */
    @Operation(summary = "세금계산서 영업점별 조회 요청", description = "세금계산서 영업점별 조회 진행됩니다.", tags = { "BillController" })
    @GetMapping("/stock/mybill")
    public ResponseEntity<ResponseDTO> myBillListWithPaging(
            @RequestParam(name = "offset", defaultValue = "1") String offset,
            @RequestParam(name = "store", defaultValue = "")int store,
            @RequestParam(name = "startDate", defaultValue = "") Date startDate,
            @RequestParam(name = "endDate", defaultValue = "")Date endDate
    ){

        int total = billService.myBillListSize(store, startDate, endDate);

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        List<Tuple> tuplePage = billService.myBillList(cri, store, startDate, endDate);

        List<BillInfoDTO> billPage = tuplePage.stream()
                .map(tuple -> {
                    BigDecimal billNo =  tuple.get(0, BigDecimal.class);
                    BigDecimal orderNo =  tuple.get(1, BigDecimal.class);
                    String storeInfoName = tuple.get(2, String.class);
                    String registDate = tuple.get(3, String.class);

                    return new BillInfoDTO(billNo.intValue(), orderNo.intValue(), storeInfoName, registDate);
                })
                .collect(Collectors.toList());


        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(billPage);
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 계산서 조회 - 주문번호별 계산서 조회 */
    @Operation(summary = "세금계산서 주문번호별 조회 요청", description = "세금계산서 주문번호별 조회 진행됩니다.", tags = { "BillController" })
    @GetMapping("/stock/bill/detail")
    public ResponseEntity<ResponseDTO> selectBill(
            @RequestParam(name = "orderNo", defaultValue = "")int orderNo){
        System.out.println("orderNo =============> " + orderNo);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  billService.selectBill(orderNo)));
    }



}
