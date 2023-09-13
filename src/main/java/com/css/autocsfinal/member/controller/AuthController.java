package com.css.autocsfinal.member.controller;

import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.market.service.EmailService;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.service.AuthService;
import com.css.autocsfinal.member.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import com.css.autocsfinal.member.dto.MemberDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "권한 API")
public class AuthController {
    private final AuthService authService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    private final MemberService memberService;

    public AuthController(AuthService authService, EmailService emailService, PasswordEncoder passwordEncoder, MemberService memberService) {
        this.authService = authService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.memberService = memberService;
    }

    @Operation(summary = "직원 로그인 요청", description = "로그인 및 인증이 진행됩니다.", tags = {"AuthController"})
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody MemberDTO memberDTO) {

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "로그인 성공", authService.login(memberDTO)));
    }

    @Operation(summary = "영업점 로그인 요청", description = "로그인 및 인증이 진행됩니다.", tags = {"AuthController"})
    @PostMapping("/login2")
    public ResponseEntity<ResponseDTO> loginGo(@RequestBody MemberDTO memberDTO) {

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "로그인 성공", authService.login2(memberDTO)));
    }

    //임시 비밀번호 발급
    private String generateRandomPassword() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    //임시 비밀번호 발급
    @Operation(summary = "임시 비밀번호 발급 요청", description = "임시 비밀번호를 발급합니다.", tags = {"AuthController"})
    @GetMapping("/ChangePwd")
    public ResponseEntity<ResponseDTO> ChangePwd(@RequestParam String name, @RequestParam String Id, @RequestParam String email) {
        try {
            //임시 비밀번호 생성
            String newPwd = generateRandomPassword();

            //임시 비밀번호 확인
            log.info("newPwd>>>>>>>>>>>>>>>>>>>>>>{}", newPwd);

            //임시 비밀번호 이메일 발송
            emailService.sendNewPwd(email, newPwd);

            //id값으로 멤버을 찾아서 newPwd를 암호화 하여 저장
            // id값으로 멤버를 찾아 비밀번호 암호화하여 저장
            Member foundMember = memberService.findMemberById(Id);
            if (foundMember != null) {
                String encodedPassword = passwordEncoder.encode(newPwd);
                foundMember.setPwd(encodedPassword);
                memberService.updateMember(foundMember);

                HttpStatus httpStatus = HttpStatus.OK;
                ResponseDTO responseDTO = new ResponseDTO(httpStatus, "임시 비밀번호 발송 및 업데이트 완료", name);
                return ResponseEntity.status(httpStatus).body(responseDTO);
            } else {
                HttpStatus httpStatus = HttpStatus.NOT_FOUND;
                ResponseDTO responseDTO = new ResponseDTO(httpStatus, "멤버를 찾을 수 없습니다.", null);
                return ResponseEntity.status(httpStatus).body(responseDTO);
            }
        } catch (Exception e) {
            log.error("Error while updating member password: {}", e.getMessage());
            HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            ResponseDTO responseDTO = new ResponseDTO(httpStatus, "비밀번호 업데이트 중 오류가 발생했습니다.", null);
            return ResponseEntity.status(httpStatus).body(responseDTO);
        }
    }
}




