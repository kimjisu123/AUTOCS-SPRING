package com.css.autocsfinal.mail.controller;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.common.PageDTO;
import com.css.autocsfinal.common.PagingResponseDTO;
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

    @GetMapping("/mail/{employeeNo}/{page}")
    public ResponseEntity<ResponseDTO> findMail(@PathVariable int employeeNo,@PathVariable(name = "page", required = false ) int offset){

        int total = mailService.findByMailAllTotal(employeeNo);

        Criteria cri = new Criteria(Integer.valueOf(offset), 8);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(mailService.findMail(employeeNo, cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "조회 성공",  pagingResponseDTO));
    }


    @GetMapping("/mailBookmark/{page}/{search}")
    public ResponseEntity<ResponseDTO> findMailbookmark(@PathVariable(name = "page", required = false ) int offset, @PathVariable(name = "search", required = false ) String title){


        int total = mailService.findByMailTotal();

        Criteria cri = new Criteria(Integer.valueOf(offset), 8);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        if(title.equals("절대로아무도검색하지않을만한값입니다.")){
            pagingResponseDTO.setData(mailService.mailBookmark(cri));
        } else{
            pagingResponseDTO.setData(mailService.mailBookmark(cri, title));
        }
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "북마크 조회 성공", pagingResponseDTO));
    }



    @GetMapping("/mailSent/{employeeNo}/{page}")
    public ResponseEntity<ResponseDTO> findMailSent(@PathVariable int employeeNo, @PathVariable(name = "page", required = false ) int offset){

        log.info("test===================================>{}", offset);

        int total = mailService.findByMailSentTotal(employeeNo);

        Criteria cri = new Criteria(Integer.valueOf(offset), 8);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(mailService.mailSent(employeeNo, cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "보낸 메일 조회 성공", pagingResponseDTO));
    }



    @PostMapping("/mail/{employeeNo}")
    public ResponseEntity<ResponseDTO> saveMail(@RequestBody MailDTO mailDTO, @PathVariable int employeeNo){

        log.info("Mail===========================================>{}", mailDTO);

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
