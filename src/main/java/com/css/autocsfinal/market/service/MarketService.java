package com.css.autocsfinal.market.service;

import com.css.autocsfinal.market.dto.ApplyFileDTO;
import com.css.autocsfinal.market.dto.ApplyFormDTO;
import com.css.autocsfinal.market.dto.ApplyFormNApplyFileDTO;
import com.css.autocsfinal.market.dto.StoreInfoDTO;
import com.css.autocsfinal.market.entity.ApplyFile;
import com.css.autocsfinal.market.entity.ApplyFormAndApplyFile;
import com.css.autocsfinal.market.entity.ApplyFormNApplyFile;
import com.css.autocsfinal.market.entity.StoreInfo;
import com.css.autocsfinal.market.repository.ApplyFormNApplyFileRepository;
import com.css.autocsfinal.market.repository.MarketApplyRepository;
import com.css.autocsfinal.market.repository.MarketRepository;
import com.css.autocsfinal.market.repository.StoreInfoRepository;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.MemberRepository;
import com.css.autocsfinal.util.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MarketService {

    private final BCryptPasswordEncoder passwordEncoder;

    /* 이미지 저장 할 위치 및 응답 할 이미지 주소 */
    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;

    private final ModelMapper modelMapper;

    private final MarketRepository marketRepository;

    private final MarketApplyRepository marketApplyRepository;

    private final ApplyFormNApplyFileRepository applyFormNApplyFileRepository;

    private final MemberRepository memberRepository;

    private final EmailService emailService;

    private final StoreInfoRepository storeInfoRepository;

    public MarketService(ModelMapper modelMapper, MarketRepository marketRepository, MarketApplyRepository marketApplyRepository, ApplyFormNApplyFileRepository applyFormNApplyFileRepository, MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder, EmailService emailService, StoreInfoRepository storeInfoRepository) {
        this.modelMapper = modelMapper;
        this.marketRepository = marketRepository;
        this.marketApplyRepository = marketApplyRepository;
        this.applyFormNApplyFileRepository = applyFormNApplyFileRepository;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.storeInfoRepository = storeInfoRepository;
    }

    @Transactional
    public String insertApply(ApplyFormDTO applyFormDTO, MultipartFile fileImage) {
        log.info("[MarketService] 영업점 신청폼 Insert Start ===================");
        log.info("[MarketService] applyDTO {} =======> " + applyFormDTO);
        log.info("[MarketService] fileImage {} =======> " + fileImage);

        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        int result = 0; // 결과에 따른 값을 구분하기 위한 용도의 변수

        try {
            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, fileImage);

            Integer maxFileNo = marketRepository.findMaxFileNo();
            int newFileNo = (maxFileNo != null) ? maxFileNo + 1 : 1;

            log.info("[ProductService] insert Image Name : {}", replaceFileName);

            // ApplyFileDTO 객체 생성 및 값 설정
            ApplyFileDTO applyFileDTO = new ApplyFileDTO();
            applyFileDTO.setFileNo(newFileNo);

            // ApplyFile 엔티티로 매핑하고 저장
            ApplyFile insertForm = modelMapper.map(applyFileDTO, ApplyFile.class);
            insertForm.setOrignal(replaceFileName);
            insertForm.setChange("표준가맹계약서" + newFileNo);
            insertForm.setRegistDate(Date.valueOf(LocalDate.now()));
            insertForm.setKine("표준가맹계약서");

            ApplyFile savedFile = marketRepository.save(insertForm);

            Integer savedFileNo = savedFile.getFileNo();


            // ApplyFormAndApplyFile 객체 생성 및 매핑
            ApplyFormAndApplyFile insertMarketForm = modelMapper.map(applyFormDTO, ApplyFormAndApplyFile.class);
            insertMarketForm.setFileNo(savedFileNo);
            insertMarketForm.setState("W");

            // ApplyFormAndApplyFile 객체 생성 및 매핑
            ApplyFormAndApplyFile applyForm = marketApplyRepository.save(insertMarketForm);

            log.info("[MarketService] insertApply End ===================");
            return (applyForm != null) ? "영업점 신청폼 등록 성공" : "영업점 신청폼 등록 실패";
        } catch (Exception e) {
            log.error("Error while inserting apply form: {}", e.getMessage());
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
    }

    //영업점 계정 발급 대기 중인 것 조회
    public List<ApplyFormNApplyFileDTO> getMarketStateW() {
        log.info("[MarketService] 계정 발급 전 영업점 신청 조회 Start ===================");

        List<ApplyFormNApplyFile> marketList = applyFormNApplyFileRepository.findAll();
        log.info("marketList : " + marketList);

        List<ApplyFormNApplyFileDTO> marketDTOList = marketList.stream()
                .map(applyFormNApplyFile -> {
                    ApplyFormNApplyFileDTO applyFormNApplyFileDTO = new ApplyFormNApplyFileDTO();

                    applyFormNApplyFileDTO.setApplyNo(applyFormNApplyFile.getApplyNo());
                    applyFormNApplyFileDTO.setName(applyFormNApplyFile.getName());
                    applyFormNApplyFileDTO.setAddress(applyFormNApplyFile.getAddress());
                    applyFormNApplyFileDTO.setRegistDate(applyFormNApplyFile.getFile().getRegistDate());
                    applyFormNApplyFileDTO.setEmail(applyFormNApplyFile.getEmail());
                    applyFormNApplyFileDTO.setLicense(applyFormNApplyFile.getLicense());
                    applyFormNApplyFileDTO.setState(applyFormNApplyFile.getState());
                    applyFormNApplyFileDTO.setFileUrl(IMAGE_URL + applyFormNApplyFile.getFile().getOrignal());

                    return applyFormNApplyFileDTO;
                })
                .collect(Collectors.toList());

        return marketDTOList;
    }

    //영업점 계정 생성
    @Transactional
    public String insertIdPwd(MemberDTO memberDTO, StoreInfoDTO storeInfoDTO) {
        log.info("[MarketService] 아이디 비밀번호 발급 Start ===================");
        log.info("[AuthService] memberDTO {} =======> " + memberDTO);

        /* check
         * 값을 받은건 MemberDTO클래스이다. 여기 담긴 값을 repository를 통해서 쿼리를 요청해야한다.
         * 그래서 현재 MemberDTO객체를 entity 객체인 Member로 변경해주는 작업 필요하다. */
        Member registMember = modelMapper.map(memberDTO, Member.class);

        // 아이디 생성
        int nextMemberCode = memberRepository.maxMemberCode() + 1;
        String newUserId = "Store" + nextMemberCode;

        // 랜덤한 비밀번호 생성
        String newPassword = generateRandomPassword();

        log.info("암호화 전 비밀번호 ============================>>>>>>>>>>>>>>>>>>>>>>>>>>> " + newPassword);

        // 아이디와 임시 비밀번호를 이메일로 전송
        String storeEmail = storeInfoDTO.getEmail();
        emailService.sendEmail(storeEmail, newUserId, newPassword);

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(newPassword);

        registMember.setId(newUserId);
        registMember.setPwd(encodedPassword);
        registMember.setState("Y");
        registMember.setRole("STORE");

        Member result = memberRepository.save(registMember);

        log.info("[AuthService] MarketInsert Result {}", (result != null) ? "계정 생성 성공" : "계정 생성 실패");

        return (result != null) ? "계정 생성 성공" : "계정 생성 실패";
    }

    private String generateRandomPassword() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    //영업점 Info등록
    @Transactional
    public String insertMarket(StoreInfoDTO storeInfoDTO) {
        log.info("[MarketService] 영업점 등록 Start ===================");
        log.info("[MarketService] storeInfoDTO : " + storeInfoDTO);

        // 최신 Member 코드 조회
        Integer maxMemberCode = memberRepository.maxMemberCode();
        log.info("maxMemberCode================> {}", maxMemberCode);

        storeInfoDTO.setRefMemberNo(maxMemberCode);

        StoreInfo insertMarket = modelMapper.map(storeInfoDTO, StoreInfo.class);
        log.info("================> {}", insertMarket);

        StoreInfo savedStore = storeInfoRepository.save(insertMarket);

        // license 번호를 사용하여 ApplyForm 조회
        ApplyFormNApplyFile applyForm = applyFormNApplyFileRepository.findByLicense(storeInfoDTO.getLicense());

        // ApplyForm 상태 변경
        if (applyForm != null) {
            applyForm.setState("O");
            applyFormNApplyFileRepository.save(applyForm);
        }

        log.info("[MarketService] insertMarket End ===================");
        return (savedStore != null) ? "영업점 등록 성공" : "영업점 등록 실패";
    }
}