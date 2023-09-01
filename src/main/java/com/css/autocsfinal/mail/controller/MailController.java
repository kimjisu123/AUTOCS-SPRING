package com.css.autocsfinal.mail.controller;

import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.mail.dto.MailDTO;
import com.css.autocsfinal.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class MailController {

    private final MailService mailService;

    @GetMapping("/mail/{employeeNo}")
    public ResponseEntity<ResponseDTO> findMail(@PathVariable int employeeNo){

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "조회 성공",  mailService.findMail(employeeNo)));
    }
    @GetMapping("/mailBookmark")
    public ResponseEntity<ResponseDTO> findMailbookmark(){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "북마크 조회 성공", mailService.mailBookmark()));
    }

    @GetMapping("/mailSent/{employeeNo}")
    public ResponseEntity<ResponseDTO> findMailSent(@PathVariable int employeeNo){



        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "보낸 메일 조회 성공", mailService.mailSent(employeeNo)));
    }



    @PostMapping("/mail/{employeeNo}")
    public ResponseEntity<ResponseDTO> saveMail(@RequestBody MailDTO mailDTO, @PathVariable int employeeNo){


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "등록 성공", mailService.saveMail(mailDTO, employeeNo)));
    }

    @PutMapping("/mail")
    public ResponseEntity<ResponseDTO> putMail(@RequestBody MailDTO paramValue){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수정 성공", mailService.setMail(paramValue)));
    }

    @DeleteMapping("/mail")
    public ResponseEntity<ResponseDTO> deleteMail(){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "전체 삭제 성공.", mailService.deleteMail()));
    }

    @DeleteMapping("/selectMail")
    public ResponseEntity<ResponseDTO> selectDeleteMail(@RequestBody MailDTO mailDTO){


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "1개의 메일 삭제 성공", mailService.deleteSelectMail(mailDTO)));
    }

}
