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

    @GetMapping("/mailBookmark")
    public ResponseEntity<ResponseDTO> findMaillbookmark(){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "북마크 조회 성공", mailService.mailBookmark()));
    }

    @PostMapping("/mail")
    public ResponseEntity<ResponseDTO> saveMail(){

        MailDTO mailDTO1 = new MailDTO(0, "우리", "제목4", "내용4", new Date(), "N");

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "등록 성공", mailService.saveMail(mailDTO1)));
    }

    @PutMapping("/mail")
    public ResponseEntity<ResponseDTO> putMail(@RequestBody MailDTO paramValue){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수정 성공", mailService.setMail(paramValue)));
    }

    @DeleteMapping("/mail")
    public ResponseEntity<ResponseDTO> deleteMail(){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "성공적으로 삭제가 되었습니다.", mailService.deleteMail()));
    }

}
