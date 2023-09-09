package com.css.autocsfinal.main.controller;


import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.main.dto.TodoDTO;
import com.css.autocsfinal.main.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mainContent")
public class MainController {

//    private final MainService mainService;

//    public MainController(MainService mainService) {
//        this.mainService = mainService;
//    }

//    @GetMapping("/approvallist")
//    public ResponseEntity<ResponseDTO> getTodoList() {
//
//        List<TodoDTO> todoList =
//        log.info("[MemberController] selectTodoList {}", todoList);
//        log.info("[MemberController] selectTodoList end");
//
//        HttpStatus httpStatus = HttpStatus.OK;
//        ResponseDTO responseDTO = new ResponseDTO(httpStatus ,"todo 조회" , todoList);
//        return ResponseEntity.status(httpStatus).body(responseDTO);
//
//    }

}
