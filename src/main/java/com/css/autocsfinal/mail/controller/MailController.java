package com.css.autocsfinal.mail.controller;

import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.mail.dto.MailDTO;
import com.css.autocsfinal.mail.entity.Mail;
import com.css.autocsfinal.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class MailController {

    private final MailService mailService;

    @GetMapping("/mail")
    public ResponseEntity<ResponseDTO> findMail(){


        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "조회 성공",  mailService.findMail()));
    }

    @PostMapping("/mail1")
    public ResponseEntity<ResponseDTO> saveMail(){

        MailDTO mailDTO1 = new MailDTO(0, "우리", "제목4", "내용4", new Date(), 'N');

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "등록 성공", mailService.saveMail(mailDTO1)));
    }
}
