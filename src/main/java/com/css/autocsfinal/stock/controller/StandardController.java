package com.css.autocsfinal.stock.controller;


import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.common.PageDTO;
import com.css.autocsfinal.common.PagingResponseDTO;
import com.css.autocsfinal.stock.dto.StandardDTO;
import com.css.autocsfinal.stock.service.StandardService;
import com.css.autocsfinal.common.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class StandardController {

    private final StandardService standardService;

    public StandardController(StandardService standardService) {
        this.standardService = standardService;
    }


    /* 입력 */
    @PostMapping("/stock/standard")
    public ResponseEntity<ResponseDTO> insertStandard(@ModelAttribute StandardDTO standardDTO){

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "규격 입력 성공"
                        , standardService.insertStandard(standardDTO)));
    }

    /* 규격 조회(물품등록) */
    @GetMapping("/stock/standard/all")  //productregist
    public ResponseEntity<ResponseDTO> selectStandardList(){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", standardService.selectStandardList()));
    }

    /* 규격 조회 페이징(카데고리등록) */
    @GetMapping("/stock/standard")
    public ResponseEntity<ResponseDTO> selectStandardListWithPaging(
            @RequestParam(name = "offset", defaultValue = "1") String offset){

        int total = standardService.selectStandardAll();

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(standardService.selectCStandardListWithPaging(cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /*수정 */
    @PutMapping("/stock/standard")
    public ResponseEntity<ResponseDTO> updateStandard(@ModelAttribute StandardDTO standardDTO) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "규격 수정 성공",  standardService.updateStandard(standardDTO)));
    }

}
