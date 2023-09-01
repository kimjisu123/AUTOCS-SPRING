package com.css.autocsfinal.market.controller;

import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.market.dto.ApplyFormDTO;
import com.css.autocsfinal.market.dto.ApplyFormNApplyFileDTO;
import com.css.autocsfinal.market.dto.StoreInfoDTO;
import com.css.autocsfinal.market.service.EmailService;
import com.css.autocsfinal.market.service.MarketService;
import com.css.autocsfinal.member.dto.MemberDTO;
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

    public MarketController(MarketService marketService, EmailService emailService) {
        this.marketService = marketService;
        this.emailService = emailService;
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
    public ResponseEntity<ResponseDTO> insertMarket(@RequestBody StoreInfoDTO storeInfoDTO, MemberDTO memberDTO) {

        log.info("StoreInfoDTO===========================> {}", storeInfoDTO);

        //영업점 등록 전 아이디와 임시비밀번호발급
        String result1 = marketService.insertIdPwd(memberDTO, storeInfoDTO);

        //영업점 등록
        String resultMessage = marketService.insertMarket(storeInfoDTO);

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

        log.info("[MarketController] fileImage {} =======> " + fileImage);

//        //영업점 번호 찾아서 OutForm등록
//        String resultMessage = marketService.insertOutApply(storeInfoDTO, fileImage);
//
//        HttpStatus httpStatus = (resultMessage.contains("성공")) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
//
//        return ResponseEntity
//                .status(httpStatus)
//                .body(new ResponseDTO(httpStatus, resultMessage, null));
//    }

        return null;
    }
}