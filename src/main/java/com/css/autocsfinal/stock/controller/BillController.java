package com.css.autocsfinal.stock.controller;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.common.PageDTO;
import com.css.autocsfinal.common.PagingResponseDTO;
import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.stock.dto.BillDTO;
import com.css.autocsfinal.stock.dto.CategoryDTO;
import com.css.autocsfinal.stock.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }


    /* 계산서 입력 */
    @PostMapping("/stock/myorderlist")
    public ResponseEntity<ResponseDTO> insertBill(@ModelAttribute BillDTO billDTO){

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "계산서 입력 성공"
                        , billService.insertBill(billDTO)));
    }


    /* 계산서 조회 페이징*/
    @GetMapping("/stock/bill")
    public ResponseEntity<ResponseDTO> selectCategoryListWithPaging(
            @RequestParam(name = "offset", defaultValue = "1") String offset){

        int total = billService.selectBillAll();

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(billService.selectBillListWithPaging(cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }



}
