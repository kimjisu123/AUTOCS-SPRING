package com.css.autocsfinal.mail.controller;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.common.PageDTO;
import com.css.autocsfinal.common.PagingResponseDTO;
import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.mail.dto.MailDTO;
import com.css.autocsfinal.mail.entity.Mail;
import com.css.autocsfinal.mail.service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.management.Notification;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
@Tag(name = "Mail", description = "쪽지함 API")
public class MailController {

    private final MailService mailService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    // 메일 조회
    @GetMapping("/mail/{employeeNo}/{page}/{search}")
    @Operation(summary = "쪽지함 화면", description = "로그인된 직원의 정보를 가져와 해당 직원의 받은 쪽지를 출력합니다.", tags = {"WorkStatusController"})
    public ResponseEntity<ResponseDTO> findMail(@PathVariable int employeeNo,
                                                @PathVariable(name = "page", required = false ) int offset,
                                                @PathVariable(name = "search", required = false ) String title){

        int total;
        log.info("============================================>{}", offset);
        Criteria cri = new Criteria(offset,  16);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

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
    @Operation(summary = "보낸 쪽지함 화면", description = "로그인된 직원의 정보를 가져와 해당 직원이 보낸 쪽지를 출력합니다.", tags = {"WorkStatusController"})
    public ResponseEntity<ResponseDTO> findMailSent(@PathVariable int employeeNo, @PathVariable(name = "page", required = false ) int offset, @PathVariable(name = "search", required = false ) String title){

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
    @Operation(summary = "북마크 쪽지함 화면", description = "로그인된 직원의 정보를 가져와 해당 직원의 북마크로 지정한 쪽지를 출력합니다.", tags = {"WorkStatusController"})
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
    @Operation(summary = "쪽지함 화면", description = "쪽지를 작성하여 상대에게 쪽지를 보냅니다.", tags = {"WorkStatusController"})
    public ResponseEntity<ResponseDTO> saveMail(@RequestBody MailDTO mailDTO, @PathVariable int employeeNo){

        try {

            mailService.saveMail(mailDTO, employeeNo);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDTO(HttpStatus.CREATED, "쪽지 전송 성공", null));

        } catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "쪽지 전송 실패", null));
        }
    }

    @PutMapping("/mail")
    @Operation(summary = "쪽지함 화면", description = "쪽지를 북마크에 등록 합니다.", tags = {"WorkStatusController"})
    public ResponseEntity<ResponseDTO> putMail(@RequestBody MailDTO paramValue){

        try{
            mailService.setMail(paramValue);

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e){

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "북마크 등록 실패", null));
        }
    }

    @DeleteMapping("/mail/{employeeNo}")
    @Operation(summary = "쪽지함 화면", description = "직원이 받은 모든 메일을 삭제합니다", tags = {"WorkStatusController"})
    public ResponseEntity<ResponseDTO> deleteMail(@PathVariable int employeeNo){

        try{
            mailService.deleteMail(employeeNo);

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e){

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "쪽지 전체 삭제 실패.", null));
        }
    }

    @DeleteMapping("/selectMail")
    @Operation(summary = "쪽지함 화면", description = "직원이 받은 메일 중 하나를 지정하여 삭제합니다", tags = {"WorkStatusController"})
    public ResponseEntity<ResponseDTO> selectDeleteMail(@RequestBody MailDTO mailDTO){

        try{
            mailService.deleteSelectMail(mailDTO);

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT).build();

        } catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "1개의 메일 삭제 실패", null));
        }
    }

    @PostMapping("/readMail")
    public ResponseEntity<ResponseDTO> readMail(@RequestBody MailDTO mailDTO){

        try{
            mailService.readMail(mailDTO);

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e){

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "메일 읽음 처리 실패", null));
        }
    }

    @MessageMapping("/mail/{employeeNo}")
    public void sendNotification(@DestinationVariable String employeeNo, Message message){

        log.info("Test 입니다==================>{}", employeeNo);

        int intEmplNo = Integer.parseInt(employeeNo);

        List<MailDTO> mailList = mailService.readMailList(240);

        String mailCount  = String.valueOf(mailList.size());
        simpMessagingTemplate.convertAndSend("/topic/mail", mailCount);

    }

}
