package com.css.autocsfinal.stock.controller;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.common.PageDTO;
import com.css.autocsfinal.common.PagingResponseDTO;
import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.stock.dto.CategoryDTO;
import com.css.autocsfinal.stock.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /* 카테고리 입력 */
    @PostMapping("/stock/category")
    public ResponseEntity<ResponseDTO> insertCategory(@ModelAttribute CategoryDTO categoryDTO){

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "카테고리 입력 성공"
                        , categoryService.insertCategory(categoryDTO)));
    }

    /* 카테고리 조회 */
    @GetMapping("/stock/category")
    public ResponseEntity<ResponseDTO> selectCategoryListWithPaging(
            @RequestParam(name = "offset", defaultValue = "1") String offset){

        int total = categoryService.selectCategoryAll();

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(categoryService.selectCategoryListWithPaging(cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    /* 카테고리 수정 */
    @PutMapping(value = "/stock/category")
    public ResponseEntity<ResponseDTO> updateCategory(@ModelAttribute CategoryDTO categoryDTO) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "카테고리 수정 성공",  categoryService.updateCategory(categoryDTO)));
    }


}
