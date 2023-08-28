package com.css.autocsfinal.market.service;

import com.css.autocsfinal.market.dto.ApplyFormAndApplyFileDTO;
import com.css.autocsfinal.market.dto.ApplyFormDTO;
import com.css.autocsfinal.market.entity.ApplyFile;
import com.css.autocsfinal.market.entity.ApplyFormAndApplyFile;
import com.css.autocsfinal.market.repository.MarketApplyRepository;
import com.css.autocsfinal.market.repository.MarketRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class MarketService {

    @Value("${market.MarketGetFormFileDirectoryPath}")
    private String MarketGetFormFileDirectoryPath;

    private final ModelMapper modelMapper;

    private final MarketRepository marketRepository;

    private final MarketApplyRepository marketApplyRepository;

    public MarketService(ModelMapper modelMapper, MarketRepository marketRepository, MarketApplyRepository marketApplyRepository) {
        this.modelMapper = modelMapper;
        this.marketRepository = marketRepository;
        this.marketApplyRepository = marketApplyRepository;
    }

    @Transactional
    public String insertApply(ApplyFormDTO applyFormDTO, List<MultipartFile> paramFileList) {
        log.info("[MarketService] 영업점 신청폼 Insert Start ===================");
        log.info("[MarketService] applyDTO {} =======> " + applyFormDTO);
        log.info("[MarketService] paramFileList {} =======> " + paramFileList);

        ApplyFormAndApplyFile insertMarketForm = modelMapper.map(applyFormDTO, ApplyFormAndApplyFile.class);

        String saveFileDirectoryPath = MarketGetFormFileDirectoryPath;
        // 파일 처리 예시
        if (!paramFileList.isEmpty()) {
            MultipartFile paramFile = paramFileList.get(0);
            try {
                String originFileName = paramFile.getOriginalFilename();
                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String savedFileName = System.currentTimeMillis() + "_" + originFileName;
                String filePath = saveFileDirectoryPath + File.separator + savedFileName;
                File dest = new File(filePath);

                // 파일 업로드 로직 추가 (MultipartFile을 File로 저장)
                paramFile.transferTo(dest);

                // 파일 정보를 엔티티에 설정
                ApplyFile applyFile = new ApplyFile();
                Integer maxFileNo = marketRepository.findMaxFileNo();
                int newFileNo = (maxFileNo != null) ? maxFileNo + 1 : 1;
                applyFile.setFileNo(newFileNo);

                insertMarketForm.getFile().setFileNo(applyFile.getFileNo());
                insertMarketForm.getFile().setOrignal(originFileName);
                insertMarketForm.getFile().setChange(savedFileName);
                insertMarketForm.getFile().setRegistDate(LocalDate.now());
                insertMarketForm.getFile().setKine("표준가맹계약서");

                // 파일 삭제 (원본 파일)
                boolean isDeleted = dest.delete();
                if (isDeleted) {
                    System.out.println(originFileName + " 원본 파일 삭제 완료!");
                } else {
                    System.out.println(originFileName + " 원본 파일 삭제 실패!");
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("파일 업로드 실패");
            }
        }

        // ApplyFormAndApplyFile 객체 생성 및 매핑
        ApplyFormAndApplyFile applyForm = marketApplyRepository.save(insertMarketForm);

        log.info("[MarketService] insertApply End ===================");
        return (applyForm != null) ? "영업점 신청폼 등록 성공" : "영업점 신청폼 등록 실패";
    }
}