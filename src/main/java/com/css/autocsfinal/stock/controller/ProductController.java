package com.css.autocsfinal.stock.controller;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.common.PageDTO;
import com.css.autocsfinal.common.PagingResponseDTO;
import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.stock.dto.ProductDTO;
import com.css.autocsfinal.stock.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /* 물품 전체조회 (불용일등록)*/
    @GetMapping("/stock/productdelete")
    public ResponseEntity<ResponseDTO> selectProductListWithPaging(
            @RequestParam(name = "offset", defaultValue = "1") String offset){

        log.info("[ProductController] selectProductListWithPaging Start ============ ");
        log.info("[ProductController] selectProductListWithPaging offset : {} ", offset);

        int total = productService.selectProductAll();

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        /* 1. offset의 번호에 맞는 페이지에 뿌려줄 Product들 */
        pagingResponseDTO.setData(productService.selectProductListWithPaging(cri));
        /* 2. PageDTO : 화면에서 페이징 처리에 필요한 정보들 */
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        log.info("[ProductController] selectProductListWithPaging End ============ ");
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 물품 조회 - 이름검색 (불용일등록_팝업창) */
    @GetMapping("/ListPopup")
    public ResponseEntity<ResponseDTO> selectProductListByNameWithPaging(
            @RequestParam(name = "offset", defaultValue = "1") String offset,
            @RequestParam(name = "s", defaultValue = "")String search){

        int total = productService.selectProductListByName(search);

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(productService.selectProductListByNameWithPaging(cri, search));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 물품 입력 */
    @PostMapping("/stock/productregist")
    public ResponseEntity<ResponseDTO> insertProduct(@ModelAttribute ProductDTO productDTO){

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "물품 입력 성공"
                        , productService.insertProduct(productDTO)));
    }

}
