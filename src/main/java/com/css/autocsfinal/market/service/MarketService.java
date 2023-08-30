//package com.css.autocsfinal.market.service;
//
//import com.css.autocsfinal.market.dto.ApplyFileDTO;
//import com.css.autocsfinal.market.dto.ApplyFormDTO;
//import com.css.autocsfinal.market.entity.ApplyFile;
//import com.css.autocsfinal.market.entity.ApplyFormAndApplyFile;
//import com.css.autocsfinal.market.repository.MarketApplyRepository;
//import com.css.autocsfinal.market.repository.MarketRepository;
//import com.css.autocsfinal.util.FileUploadUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.transaction.Transactional;
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.UUID;
//
//@Service
//@Slf4j
//public class MarketService {
//
////    @Value("${market.MarketGetFormFileDirectoryPath}")
////    private String MarketGetFormFileDirectoryPath;
//
//    /* 이미지 저장 할 위치 및 응답 할 이미지 주소 */
//    @Value("${image.image-dir}")
//    private String IMAGE_DIR;
//    @Value("${image.image-url}")
//    private String IMAGE_URL;
//
//    private final ModelMapper modelMapper;
//
//    private final MarketRepository marketRepository;
//
//    private final MarketApplyRepository marketApplyRepository;
//
//    public MarketService(ModelMapper modelMapper, MarketRepository marketRepository, MarketApplyRepository marketApplyRepository) {
//        this.modelMapper = modelMapper;
//        this.marketRepository = marketRepository;
//        this.marketApplyRepository = marketApplyRepository;
//    }
//
//    @Transactional
//    public String insertApply(ApplyFormDTO applyFormDTO, MultipartFile fileImage) {
//        log.info("[MarketService] 영업점 신청폼 Insert Start ===================");
//        log.info("[MarketService] applyDTO {} =======> " + applyFormDTO);
//        log.info("[MarketService] fileImage {} =======> " + fileImage);
//
//        String imageName = UUID.randomUUID().toString().replace("-", "");
//        String replaceFileName = null;
//        int result = 0; // 결과에 따른 값을 구분하기 위한 용도의 변수
//
//        try {
//            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, fileImage);
//
//            Integer maxFileNo = marketRepository.findMaxFileNo();
//            int newFileNo = (maxFileNo != null) ? maxFileNo + 1 : 1;
//
//            log.info("[ProductService] insert Image Name : {}", replaceFileName);
//
//            // ApplyFileDTO 객체 생성 및 값 설정
//            ApplyFileDTO applyFileDTO = new ApplyFileDTO();
//            applyFileDTO.setFileNo(newFileNo);
//
//            // ApplyFile 엔티티로 매핑하고 저장
//            ApplyFile insertForm = modelMapper.map(applyFileDTO, ApplyFile.class);
//            insertForm.setOrignal(replaceFileName);
//            insertForm.setChange("표준가맹계약서" + newFileNo);
//            insertForm.setRegistDate(Date.valueOf(LocalDate.now()));
//            insertForm.setKine("표준가맹계약서");
//
//            ApplyFile savedFile = marketRepository.save(insertForm);
//
//            Integer savedFileNo = savedFile.getFileNo();
//
//
//            // ApplyFormAndApplyFile 객체 생성 및 매핑
//            ApplyFormAndApplyFile insertMarketForm = modelMapper.map(applyFormDTO, ApplyFormAndApplyFile.class);
//            insertMarketForm.setFileNo(savedFileNo);
//            insertMarketForm.setState("W");
//
//            // ApplyFormAndApplyFile 객체 생성 및 매핑
//            ApplyFormAndApplyFile applyForm = marketApplyRepository.save(insertMarketForm);
//
//            log.info("[MarketService] insertApply End ===================");
//            return (applyForm != null) ? "영업점 신청폼 등록 성공" : "영업점 신청폼 등록 실패";
//        } catch (Exception e) {
//            log.error("Error while inserting apply form: {}", e.getMessage());
//            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
//            throw new RuntimeException(e);
//        }
//    }
//}