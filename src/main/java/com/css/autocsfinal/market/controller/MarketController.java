package com.css.autocsfinal.market.controller;

import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.market.dto.*;
import com.css.autocsfinal.market.entity.ApplyFormNApplyFile;
import com.css.autocsfinal.market.entity.OutFile;
import com.css.autocsfinal.market.repository.ApplyFormNApplyFileRepository;
import com.css.autocsfinal.market.repository.OutFileRepository;
import com.css.autocsfinal.market.service.EmailService;
import com.css.autocsfinal.market.service.MarketService;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.MemberRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.SecureRandom;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/market")
public class MarketController {

    private final MarketService marketService;
    private final EmailService emailService;

    private final OutFileRepository outFileRepository;

    private final ApplyFormNApplyFileRepository applyFormNApplyFileRepository;

    private final MemberRepository memberRepository;

    public MarketController(MarketService marketService, EmailService emailService, OutFileRepository outFileRepository, ApplyFormNApplyFileRepository applyFormNApplyFileRepository, MemberRepository memberRepository) {
        this.marketService = marketService;
        this.emailService = emailService;
        this.outFileRepository = outFileRepository;
        this.applyFormNApplyFileRepository = applyFormNApplyFileRepository;
        this.memberRepository = memberRepository;
    }

    @Operation(summary = "영업점 신청폼 등록 요청", description = "신청폼을 등록합니다.", tags = {"MarketController"})
    @PostMapping(value = "/applyMarket")
    public ResponseEntity<ResponseDTO> applyMarket(@ModelAttribute ApplyFormDTO applyFormDTO, MultipartFile fileImage) {

        log.info("[MarketController] fileImage {} =======> " + fileImage);

        // 영업점 신청폼 등록
        String resultMessage = marketService.insertApply(applyFormDTO, fileImage);

        HttpStatus httpStatus = (resultMessage.contains("성공")) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity
                .status(httpStatus)
                .body(new ResponseDTO(httpStatus, resultMessage, null));
    }

    //영업점 신청 대기 중인 목록 조회
    @Operation(summary = "영업점 계정 신청 대기 조회 요청", description = "영업점 계정 신청 대기 상태를 조회합니다.", tags = {"MarketController"})
    @GetMapping("/getMarketStateW")
    public ResponseEntity<ResponseDTO> getMarketStateW() {
        System.out.println("check ==========================");
        List<ApplyFormNApplyFileDTO> formNfileDTOList = marketService.getMarketStateW();

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "계정 신청 대기 중인 영업점 조회 성공", formNfileDTOList);

        return ResponseEntity.status(httpStatus).body(responseDTO);
    }

    //영업점 등록
    @Operation(summary = "영업점 계정생성 요청", description = "계정생성이 진행됩니다.", tags = {"MarketController"})
    @PostMapping("/insertMarket")
    public ResponseEntity<ResponseDTO> insertMarket(@RequestBody StoreInfo2DTO storeInfo2DTO, MemberDTO memberDTO) {

        log.info("StoreInfoDTO===========================> {}", storeInfo2DTO);

        //영업점 등록 전 아이디와 임시비밀번호발급
        String result1 = marketService.insertIdPwd(memberDTO, storeInfo2DTO);

        //영업점 등록
        String resultMessage = marketService.insertMarket(storeInfo2DTO);

        HttpStatus httpStatus = (result1.contains("성공") && resultMessage.contains("성공")) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity
                .status(httpStatus)
                .body(new ResponseDTO(httpStatus, resultMessage, null));
    }

    //영업점 아이디 찾기
    @GetMapping("/findStoreId")
    public ResponseEntity<ResponseDTO> findStoreId(@RequestParam String email, @RequestParam String name) {
        try {

            // 이름과 이메일로 Store 정보 조회
            StoreInfoDTO foundStore = marketService.findStoreInfoByNameAndEmail(email, name);

            log.info("foundStore===========================> {}", foundStore);

            if (foundStore != null) {
                HttpStatus httpStatus = HttpStatus.OK;
                ResponseDTO responseDTO = new ResponseDTO(httpStatus, "아이디 찾기 성공", foundStore);
                return ResponseEntity.status(httpStatus).body(responseDTO);
            }

            // Store 정보나 아이디가 없을 경우
            HttpStatus httpStatus = HttpStatus.NOT_FOUND;
            ResponseDTO responseDTO = new ResponseDTO(httpStatus, "사용자 정보 또는 아이디를 찾을 수 없습니다.", null);
            return ResponseEntity.status(httpStatus).body(responseDTO);
        } catch (Exception e) {
            log.error("Error while finding Store ID: {}", e.getMessage());
            HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            ResponseDTO responseDTO = new ResponseDTO(httpStatus, "아이디 찾기 중 오류가 발생했습니다.", null);
            return ResponseEntity.status(httpStatus).body(responseDTO);
        }
    }

    //비밀번호 인증번호 만들기
    @Operation(summary = "인증번호 생성", description = "인증번호를 생성합니다.", tags = {"MarketController"})
    private String generateVerificationCode() {
        // 영문 대문자, 소문자, 숫자로 이루어진 문자열
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";

        // 난수 생성기 초기화
        SecureRandom secureRandom = new SecureRandom();

        // 15자리의 인증번호 생성
        StringBuilder verificationCode = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            verificationCode.append(characters.charAt(randomIndex));
        }

        return verificationCode.toString();
    }

    //영업점 비밀번호 찾기
    @Operation(summary = "영업점 비밀번호 인증 요청", description = "인증번호 생성 후  이메일 발송합니다.", tags = {"MarketController"})
    @GetMapping("/findStorePwd")
    public ResponseEntity<ResponseDTO> findStorePwd(@RequestParam String name, @RequestParam String id, @RequestParam String employeeEmail) {
        try {
            String email = employeeEmail;

            // 이름과 이메일로 Employee 정보 조회
            StoreInfoDTO foundStore = marketService.findStoreInfoByNameAndEmail(name, email);
            log.info("foundStore>>>>>>>>>>>>>>>>>>>>>>{}", foundStore);
            log.info("foundStore.id>>>>>>>>>>>>>>>>>>>>>>{}", foundStore.getMember().getId());

            if (foundStore != null && foundStore.getMember().getId().equals(id)) {

                // 인증번호 생성
                String verificationCode = generateVerificationCode();

                //인증번호 확인
                log.info("generateVerificationCode>>>>>>>>>>>>>>>>>>>>>>{}", verificationCode);

                // 인증번호 이메일 발송
                emailService.sendVerificationCode2(email, verificationCode);

                // 결과 배열 생성 및 값 담기
                String[] resultArray = new String[]{verificationCode, email, id, name};

                HttpStatus httpStatus = HttpStatus.OK;
                ResponseDTO responseDTO = new ResponseDTO(httpStatus, "아이디 찾기 성공", resultArray);
                return ResponseEntity.status(httpStatus).body(responseDTO);
            }

            // Store 정보나 아이디가 없을 경우
            HttpStatus httpStatus = HttpStatus.NOT_FOUND;
            ResponseDTO responseDTO = new ResponseDTO(httpStatus, "사용자 정보 또는 아이디를 찾을 수 없습니다.", null);
            return ResponseEntity.status(httpStatus).body(responseDTO);
        } catch (Exception e) {
            log.error("Error while finding Store ID: {}", e.getMessage());
            HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            ResponseDTO responseDTO = new ResponseDTO(httpStatus, "아이디 찾기 중 오류가 발생했습니다.", null);
            return ResponseEntity.status(httpStatus).body(responseDTO);
        }
    }

    @Operation(summary = "영업점 계정 비활성화 신청폼 등록 요청", description = "신청폼을 등록합니다.", tags = {"MarketController"})
    @PostMapping(value = "/StoreOut")
    public ResponseEntity<ResponseDTO> StoreOut(@ModelAttribute StoreInfoDTO storeInfoDTO, MultipartFile fileImage) {

        log.info("[MarketController] storeInfoDTO {} =======> " + storeInfoDTO);
        log.info("[MarketController] fileImage {} =======> " + fileImage);

        // 영업점 계정 비활성화 신청폼 등록(이름과 번호로 매장찾기. .)
        String resultMessage = marketService.insertOut(storeInfoDTO, fileImage);

        HttpStatus httpStatus = (resultMessage.contains("성공")) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity
                .status(httpStatus)
                .body(new ResponseDTO(httpStatus, resultMessage, null));
    }

    //영업점 계정 비활성화 신청 대기 중인 목록 조회
    @Operation(summary = "영업점 계정 비활성화 신청 대기 조회 요청", description = "영업점 계정 비활성화 신청 대기 상태를 조회합니다.", tags = {"MarketController"})
    @GetMapping("/getOutMarketStateW")
    public ResponseEntity<ResponseDTO> getOutMarketStateW() {
        System.out.println("check ==========================");
        //영업점과 계정비활성화 파일DTO
        List<StoreAndOutDTO> storeAndOutDTOListDTOList = marketService.getOutMarketStateW();

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "계정 비활성화 신청 대기 중인 영업점 조회 성공", storeAndOutDTOListDTOList);

        return ResponseEntity.status(httpStatus).body(responseDTO);
    }

    //영업점 계정 비활성화(이건... 서비스로 안가네..?)
    @Operation(summary = "영업점 계정 비활성화 요청", description = "영업점 계정 비활성화를 시작합니다.", tags = {"MarketController"})
    @PostMapping("/StoreOutGOGO")
    public ResponseEntity<ResponseDTO> StoreOutGOGO(@RequestParam int fileNo, @RequestParam String license, @RequestParam int no, @RequestParam String email) {

        log.info("fileNo>>>>>>>>>>>>> {}", fileNo);
        log.info("license>>>>>>>>>>>>> {}", license);
        log.info("no>>>>>>>>>>>>> {}", no);
        log.info("email>>>>>>>>>>>>> {}", email);

        try {
            //1. 파일 번호로 해당 테이블 상태값 업데이트
            OutFile outFile = outFileRepository.findByFileNo(fileNo);
            outFile.setState('X');
            outFileRepository.save(outFile);

            //2. 라이센스 번호로 해야할듯
            ApplyFormNApplyFile applyForm = applyFormNApplyFileRepository.findByLicense(license);
            applyForm.setState("X");
            applyFormNApplyFileRepository.save(applyForm);


            //3. 멤버 번호로 상태값 업데이트(비활성화)
            Member member = memberRepository.findByNo(no);
            member.setState("N");
            memberRepository.save(member);

            //4. 이메일로 계정 비활성화 안내
            emailService.sendStoreOut(email);

           String resultMessage = "계정 비활성화가 완료되었습니다.";
           return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(HttpStatus.CREATED, resultMessage, null));
        } catch (Exception e) {
           String resultMessage = "계정 비활성화 중 오류가 발생하였습니다.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(HttpStatus.BAD_REQUEST, resultMessage, null));
        }
    }
}