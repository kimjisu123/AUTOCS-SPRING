package com.css.autocsfinal.stock.controller;

import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.stock.dto.CategoryDTO;
import com.css.autocsfinal.stock.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/stock/category")
    public ResponseEntity<ResponseDTO> insertProduct(@ModelAttribute CategoryDTO categoryDTO){

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "카테고리 입력 성공"
                        , categoryService.insertCategory(categoryDTO)));
    }
}
