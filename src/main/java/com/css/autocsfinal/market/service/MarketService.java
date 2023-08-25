package com.css.autocsfinal.market.service;

import com.css.autocsfinal.market.dto.ApplyFormAndApplyFileDTO;
import com.css.autocsfinal.market.entity.ApplyFormAndApplyFile;
import com.css.autocsfinal.market.repository.MarketRepository;
import com.css.autocsfinal.member.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class MarketService {

    private final ModelMapper modelMapper;

    private final MarketRepository marketRepository;

    public MarketService(ModelMapper modelMapper, MarketRepository marketRepository) {
        this.modelMapper = modelMapper;
        this.marketRepository = marketRepository;
    }

    @Transactional
    public String insertApply(ApplyFormAndApplyFileDTO applyDTO) {
        log.info("[MarketService] 영업점 신청폼 Insert Start ===================");
        log.info("[MarketService] applyDTO {} =======> " + applyDTO);

        ApplyFormAndApplyFile insertMarketForm = modelMapper.map(applyDTO, ApplyFormAndApplyFile.class);

        // Insert 작업 진행
        ApplyFormAndApplyFile applyForm = marketRepository.save(insertMarketForm);

        log.info("[MemberService] insertApply End ===================");
        return (applyForm != null) ? "영업점 신청폼 등록 성공" : "영업점 신청폼 등록 실패";
    }
}
