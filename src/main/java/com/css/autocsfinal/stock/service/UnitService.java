package com.css.autocsfinal.stock.service;


import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.stock.dto.UnitDTO;
import com.css.autocsfinal.stock.entity.Unit;
import com.css.autocsfinal.stock.repository.UnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UnitService {

    private final UnitRepository unitRepository;

    private final ModelMapper modelMapper;



    public UnitService(UnitRepository unitRepository, ModelMapper modelMapper) {
        this.unitRepository = unitRepository;
        this.modelMapper = modelMapper;
    }

    /* 입력 */
    @Transactional
    public String insertUnit(UnitDTO unitDTO) {

        int result = 0; // 결과에 따른 값을 구분하기위한 용도의 변수

        try{
            // 저장을 위해서 일반 DTO객체를 Entity객체로 변경
            Unit insertUnit = modelMapper.map(unitDTO, Unit.class);
            unitRepository.save(insertUnit);
            result = 1;

        } catch (Exception e){
            System.out.println("check");
            throw new RuntimeException(e);
        }

        return (result > 0)? "단위 입력 성공": "단위 입력 실패";
    }

    /* 조회 */
    public int selectUnitAll() {

        List<Unit> categotyList = unitRepository.findAll();
        return categotyList.size();
    }

    /* 사용중인 단위 조회 */
    public List<UnitDTO> selectUnitList() {

        List<Unit> result = unitRepository.findByUseYnOrderByName("Y");

        List<UnitDTO> unitList = result.stream()
                .map(unit -> modelMapper
                        .map(unit, UnitDTO.class)).collect(Collectors.toList());
        return unitList;
    }

    /* 전체 단위 조회 페이징 */
    public List<UnitDTO> selectCUnitListWithPaging(Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("useYn", "productUnitNo").descending());

        Page<Unit> result = unitRepository.findAll(paging);

        List<UnitDTO> categoryList = result.stream()
                .map(product -> modelMapper
                        .map(product, UnitDTO.class)).collect(Collectors.toList());
        return categoryList;
    }

    /* 수정 */
    @Transactional
    public String updateUnit(UnitDTO unitDTO) {

        int result = 0;

        try {

            /* update 할 엔티티 조회 */
            Unit category = unitRepository.findById(unitDTO.getProductUnitNo()).get();

            /* update를 위한 엔티티 값 수정 */
            category.setUseYn(unitDTO.getUseYn());

            result = 1;

        } catch (Exception e) {
            log.info("[updateCategory] Exception!!");
            throw new RuntimeException(e);
        }

        return (result > 0) ? "단위 업데이트 성공" : "단위 업데이트 실패";
    }

}
