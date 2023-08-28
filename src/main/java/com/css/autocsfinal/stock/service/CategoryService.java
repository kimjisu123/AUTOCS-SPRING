package com.css.autocsfinal.stock.service;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.stock.dto.CategoryDTO;
import com.css.autocsfinal.stock.entity.Category;
import com.css.autocsfinal.stock.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    /* 카테고리 입력 */
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


    public int selectCategoryAll() {

        List<Category> categotyList = categoryRepository.findAll();
        return categotyList.size();
    }

    /* 사용중인 카테고리 조회 */
    public List<CategoryDTO> selectCategoryList() {

        List<Category> result = categoryRepository.findByUseYnOrderByName("Y");

        List<CategoryDTO> categotyList = result.stream()
                .map(category -> modelMapper
                        .map(category, CategoryDTO.class)).collect(Collectors.toList());
        return categotyList;
    }

    /* 카테고리 조회 페이징 */
    public List<CategoryDTO> selectCategoryListWithPaging(Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("useYn", "productCategoryNo").descending());

        Page<Category> result = categoryRepository.findAll(paging);

        List<CategoryDTO> categoryList = result.stream()
                .map(category -> modelMapper
                        .map(category, CategoryDTO.class)).collect(Collectors.toList());
        return categoryList;
    }

    /* 카테고리 수정 */
    @Transactional
    public String updateCategory(CategoryDTO categoryDTO) {

        log.info("[CategoryService] updateCategory Start ===================================");
        log.info("[CategoryService] categoryDTO : " + categoryDTO);

        int result = 0;

        try {

            /* update 할 엔티티 조회 */
            Category category = categoryRepository.findById(categoryDTO.getProductCategoryNo()).get();

            /* update를 위한 엔티티 값 수정 */
            category.setUseYn(categoryDTO.getUseYn());

            result = 1;

        } catch (Exception e) {
            log.info("[updateCategory] Exception!!");
            throw new RuntimeException(e);
        }

        log.info("[CategoryService] updateCategory End ===================================");
        return (result > 0) ? "카테고리 업데이트 성공" : "카테고리 업데이트 실패";
    }

}
