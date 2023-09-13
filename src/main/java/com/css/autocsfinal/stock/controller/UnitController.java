package com.css.autocsfinal.stock.controller;


import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.common.PageDTO;
import com.css.autocsfinal.common.PagingResponseDTO;
import com.css.autocsfinal.common.ResponseDTO;


import com.css.autocsfinal.stock.dto.UnitDTO;
import com.css.autocsfinal.stock.service.UnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
@Tag(name = "Unit", description = "물품 단위 API")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }


    /* 입력 */
    @Operation(summary = "단위 등록 요청", description = "단위 등록 진행됩니다.", tags = { "UnitController" })
    @PostMapping("/stock/unit")
    public ResponseEntity<ResponseDTO> insertUnit(@ModelAttribute UnitDTO unitDTO){

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "단위 입력 성공"
                        , unitService.insertUnit(unitDTO)));
    }

    /* 단위 조회(물품등록) */
    @Operation(summary = "단위 전체조회 요청", description = "단위 전체조회 진행됩니다.", tags = { "UnitController" })
    @GetMapping("/stock/unit/all")  //productregist
    public ResponseEntity<ResponseDTO> selectUnitList(){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", unitService.selectUnitList()));
    }

    /* 단위 조회 페이징(카데고리등록) */
    @Operation(summary = "단위 전체조회(페이징) 요청", description = "단위 전체조회(페이징) 진행됩니다.", tags = { "UnitController" })
    @GetMapping("/stock/unit")
    public ResponseEntity<ResponseDTO> selecUnitListWithPaging(
            @RequestParam(name = "offset", defaultValue = "1") String offset){

        int total = unitService.selectUnitAll();

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(unitService.selectCUnitListWithPaging(cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /*수정 */
    @Operation(summary = "단위 수정 요청", description = "단위 수정 진행됩니다.", tags = { "UnitController" })
    @PutMapping("/stock/unit")
    public ResponseEntity<ResponseDTO> updateUnit(@ModelAttribute UnitDTO unitDTO) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "단위 수정 성공",  unitService.updateUnit(unitDTO)));
    }

}
