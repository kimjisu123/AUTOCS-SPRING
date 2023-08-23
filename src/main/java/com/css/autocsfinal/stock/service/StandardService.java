package com.css.autocsfinal.stock.service;


import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.stock.dto.StandardDTO;
import com.css.autocsfinal.stock.entity.Standard;
import com.css.autocsfinal.stock.repository.StandardRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StandardService {

    private final StandardRepository standardRepository;

    private final ModelMapper modelMapper;



    public StandardService(StandardRepository standardRepository, ModelMapper modelMapper) {
        this.standardRepository = standardRepository;
        this.modelMapper = modelMapper;
    }

    /* 입력 */
    @Transactional
    public String insertStandard(StandardDTO standardDTO) {

        int result = 0; // 결과에 따른 값을 구분하기위한 용도의 변수

        try{
            // 저장을 위해서 일반 DTO객체를 Entity객체로 변경
            Standard insertStandard = modelMapper.map(standardDTO, Standard.class);
            standardRepository.save(insertStandard);
            result = 1;

        } catch (Exception e){
            System.out.println("check");
            throw new RuntimeException(e);
        }

        return (result > 0)? "규격 입력 성공": "규격 입력 실패";
    }

    /* 조회 */
    public int selectStandardAll() {

        List<Standard> categotyList = standardRepository.findAll();
        return categotyList.size();
    }

    public List<StandardDTO> selectCStandardListWithPaging(Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("useYn", "productStandardNo").descending());

        Page<Standard> result = standardRepository.findAll(paging);

        List<StandardDTO> categoryList = result.stream()
                .map(product -> modelMapper
                        .map(product, StandardDTO.class)).collect(Collectors.toList());
        return categoryList;
    }

    /* 수정 */
    @Transactional
    public String updateStandard(StandardDTO categoryDTO) {

        int result = 0;

        try {

            /* update 할 엔티티 조회 */
            Standard category = standardRepository.findById(categoryDTO.getProductStandardNo()).get();

            /* update를 위한 엔티티 값 수정 */
            category.setUseYn(categoryDTO.getUseYn());

            result = 1;

        } catch (Exception e) {
            log.info("[updateCategory] Exception!!");
            throw new RuntimeException(e);
        }

        return (result > 0) ? "규격 업데이트 성공" : "규격 업데이트 실패";
    }

}
