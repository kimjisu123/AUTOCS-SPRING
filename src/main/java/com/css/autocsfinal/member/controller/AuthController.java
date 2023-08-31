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

    @Operation(summary = "직원 로그인 요청", description = "로그인 및 인증이 진행됩니다.", tags = {"AuthController"})
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody MemberDTO memberDTO){

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "로그인 성공", authService.login(memberDTO)));
    }

    @Operation(summary = "영업점 로그인 요청", description = "로그인 및 인증이 진행됩니다.", tags = {"AuthController"})
    @PostMapping("/login2")
    public ResponseEntity<ResponseDTO> loginGo(@RequestBody MemberDTO memberDTO){

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "로그인 성공", authService.login2(memberDTO)));
    }


}
