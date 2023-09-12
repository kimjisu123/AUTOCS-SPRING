package com.css.autocsfinal.stock.service;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.stock.dto.IoDTO;
import com.css.autocsfinal.stock.dto.IoDetailDTO;
import com.css.autocsfinal.stock.dto.IoSummaryDTO;
import com.css.autocsfinal.stock.dto.ProductDetailDTO;
import com.css.autocsfinal.stock.entity.Io;
import com.css.autocsfinal.stock.entity.IoDetail;
import com.css.autocsfinal.stock.entity.ProductDetail;
import com.css.autocsfinal.stock.repository.IoDetailRepository;
import com.css.autocsfinal.stock.repository.IoRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class IoService {

    private final IoRepository ioRepository;

    private final IoDetailRepository ioDetailRepository;
    private final ModelMapper modelMapper;

    public IoService(IoRepository ioRepository, IoDetailRepository ioDetailRepository, ModelMapper modelMapper) {
        this.ioRepository = ioRepository;
        this.ioDetailRepository = ioDetailRepository;
        this.modelMapper = modelMapper;
    }

    /* 입출고 입력 */
    @Transactional
    public String insertIo(IoDTO ioDTO) {

        int result = 0;

        try{
            Io insertIo = modelMapper.map(ioDTO, Io.class);

            insertIo.setRegistDate(new Date(System.currentTimeMillis()));

            ioRepository.save(insertIo);
            result = 1;

        } catch (Exception e){
            System.out.println("check");
            throw new RuntimeException(e);
        }

        return (result > 0)? "입출고 입력 성공": "입출고 입력 실패";
    }


    /* 입출고 조회 (페이지사이즈) */
    public int selectIoAll() {

        List<IoDetail> ioList = ioDetailRepository.findAll();

        return ioList.size();
    }


    /* 입출고 그룹화 조회 - 이름,날짜조회 (페이지사이즈) */
    public int summarizeSize(String s, Date startDate, Date endDate) {

        /* 페이징 처리 결과를 Page 타입으로 반환 받는다. */
        List<Tuple> ioList = ioRepository.summarizeSize(s, startDate, endDate);

        return ioList.size();
    }


    /* 입출고 조회 페이징*/
    public List<IoDetailDTO> selectIoListWithPaging(Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("productIoNo").descending());

        Page<IoDetail> result =ioDetailRepository.findAll(paging);

        List<IoDetailDTO> ioList = result.stream()
                .map(ioDetail -> modelMapper
                        .map(ioDetail, IoDetailDTO.class)).collect(Collectors.toList());
        return ioList;
    }


    /* 입출고 조회 그룹화 - 이름 날짜 검색, 페이징 */
    public List<Tuple> summarize(Criteria cri, String s, Date startDate, Date endDate) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count);

        Page<Tuple> result =ioRepository.summarize(s, startDate, endDate, paging);

        List<Tuple> ioList = result.stream()
                .map(tuple -> modelMapper
                        .map(tuple, Tuple.class)).collect(Collectors.toList());
        return ioList;
    }

    /* 매출통계용 - 이름 날짜 검색 */
    public List<Tuple> statistics(String s, Date startDate, Date endDate) {

        List<Tuple> result =ioRepository.statistics(s, startDate, endDate);

        return result;
    }

    /* 영업점별 매출통계용 - 이름 날짜 검색 */
    public List<Tuple> myStatistics(String store, String s, Date startDate, Date endDate) {

        List<Tuple> result =ioRepository.myStatistics(store, s, startDate, endDate);

        return result;
    }


}
