package com.css.autocsfinal.stock.service;

import com.css.autocsfinal.stock.dto.CategoryDTO;
import com.css.autocsfinal.stock.entity.Category;
import com.css.autocsfinal.stock.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public String insertCategory(CategoryDTO categoryDTO) {
        log.info("[CategoryService] CategoryService Start ===================");
        log.info("[CategoryService] categoryDTO : " + categoryDTO);

        int result = 0; // 결과에 따른 값을 구분하기위한 용도의 변수

        try{

            // 저장을 위해서 일반 DTO객체를 Entity객체로 변경
            Category insertCategory = modelMapper.map(categoryDTO, Category.class);
            categoryRepository.save(insertCategory);
            result = 1;

        } catch (Exception e){
            System.out.println("check");
            throw new RuntimeException(e);
        }


        log.info("[CategoryService] insertCategory End ===================");
        return (result > 0)? "카테고리 입력 성공": "카테고리 입력 실패";
    }
}
