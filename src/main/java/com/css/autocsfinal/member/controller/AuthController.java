package com.css.autocsfinal.member.controller;

import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.member.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.css.autocsfinal.member.dto.MemberDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    /* @RequestBody를 통해 RequestBody로 넘어온 Json문자열을 파싱해서 MemberDTO 속성으로
     *  매핑해 객체로 받아낸다(회원 아이디, 비밀번호)
     * */
    @Operation(summary = "로그인 요청", description = "로그인 및 인증이 진행됩니다.", tags = {"AuthController"})
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody MemberDTO memberDTO){

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "로그인 성공", authService.login(memberDTO)));
    }

    @Operation(summary = "본사직원 계정생성 요청", description = "계정생성이 진행됩니다.", tags = {"AuthController"})
    @PostMapping("/signupMember")
    public ResponseEntity<ResponseDTO> signupMember(@RequestBody MemberDTO memberDTO){
        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.CREATED, "계정생성 성공", authService.signupMember(memberDTO)));
    }

    @Operation(summary = "영업점 계정생성 요청", description = "계정생성이 진행됩니다.", tags = {"AuthController"})
    @PostMapping("/signupMarket")
    public ResponseEntity<ResponseDTO> signupMarket(@RequestBody MemberDTO memberDTO){
        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.CREATED, "계정생성 성공", authService.signupMarket(memberDTO)));
    }
}
