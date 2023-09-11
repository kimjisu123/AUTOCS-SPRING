package com.css.autocsfinal.main.controller;


import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.main.dto.TodoDTO;
import com.css.autocsfinal.main.service.MainService;
import com.css.autocsfinal.schedule.entity.ScheduleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mainContent")
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    // 회원별 조회
    @GetMapping("/shortDaily/{memberNo}")
    public ResponseEntity<ResponseDTO> getShortDailyList(@PathVariable int memberNo){

        log.info("[MainController] getTodoMember start");
        log.info("[MainController] getTodoMember memberNo  {}" , memberNo);

        // memberNO가 1인 사용자의 todo를 가져오는 서비스 메서드 호출
        List<ScheduleDTO> scheduleList = mainService.getScheduleByOne(memberNo);

        log.info("[MemberController] getTodoMember {}", scheduleList);
        log.info("[MemberController] getTodoMember end");

        HttpStatus httpStatus = HttpStatus.OK;
        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "멤버의 일정 조회", scheduleList);
        return ResponseEntity.status(httpStatus).body(responseDTO);
    }

}
