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


    // 메일 조회
    @GetMapping("/mail/{employeeNo}/{page}/{search}")
    public ResponseEntity<ResponseDTO> findMail(@PathVariable int employeeNo,@PathVariable(name = "page", required = false ) int offset, @PathVariable(name = "search", required = false ) String title){

        log.info("=========================================>{}", title);
        int total;

        Criteria cri = new Criteria(Integer.valueOf(offset), 8);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        // 검색 유무 확인
        if(title.equals("절대로아무도검색하지않을만한값입니다.")){
            total =  mailService.findByMailAllTotal(employeeNo);
            pagingResponseDTO.setData(mailService.findMail(employeeNo, cri));
        } else{
            total =  mailService.findByMailSelectTotal(employeeNo, title);
            pagingResponseDTO.setData(mailService.findMail(employeeNo, cri, title));
        }
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "조회 성공",  pagingResponseDTO));
    }


    // 보낸 메일 조회
    @GetMapping("/mailSent/{employeeNo}/{page}/{search}")
    public ResponseEntity<ResponseDTO> findMailSent(@PathVariable int employeeNo, @PathVariable(name = "page", required = false ) int offset, @PathVariable(name = "search", required = false ) String title){

        log.info("test===================================>{}", title);



        int total;

        Criteria cri = new Criteria(Integer.valueOf(offset), 8);
        // 검색 유무 확인
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        if(title.equals("절대로아무도검색하지않을만한값입니다.")){
            total = mailService.findByMailSentTotal(employeeNo);
            pagingResponseDTO.setData(mailService.mailSent(employeeNo, cri));
        } else{
            total = mailService.findBySelectMailSentTotal(employeeNo, title);
            pagingResponseDTO.setData(mailService.mailSent(employeeNo, cri, title));
        }

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "보낸 메일 조회 성공", pagingResponseDTO));
    }

    @GetMapping("/mailBookmark/{employeeNo}/{page}/{search}")
    public ResponseEntity<ResponseDTO> findMailbookmark(@PathVariable int employeeNo, @PathVariable(name = "page", required = false ) int offset, @PathVariable(name = "search", required = false ) String title){
        log.info("1111111111111111111111111111111111");

        int total;

        Criteria cri = new Criteria(Integer.valueOf(offset), 8);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        // 검색 유무 확인
        if(title.equals("절대로아무도검색하지않을만한값입니다.")){
            total = mailService.findByMailTotal(employeeNo);
            pagingResponseDTO.setData(mailService.mailBookmark(employeeNo, cri));
        } else{

            log.info("titl===============================================>{}", title);
            total = mailService.findByBookmarkMailTotal(employeeNo, title);
            pagingResponseDTO.setData(mailService.mailBookmark(employeeNo, cri, title));
        }
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "북마크 조회 성공", pagingResponseDTO));
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

    @DeleteMapping("/mail/{employeeNo}")
    public ResponseEntity<ResponseDTO> deleteMail(@PathVariable int employeeNo){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "전체 삭제 성공.", mailService.deleteMail(employeeNo)));
    }

    @DeleteMapping("/selectMail")
    public ResponseEntity<ResponseDTO> selectDeleteMail(@RequestBody MailDTO mailDTO){


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "1개의 메일 삭제 성공", mailService.deleteSelectMail(mailDTO)));
    }

}
