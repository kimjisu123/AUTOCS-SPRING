package com.css.autocsfinal.main.controller;


import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.mail.dto.MailDTO;
import com.css.autocsfinal.main.dto.TodoDTO;
import com.css.autocsfinal.main.entity.Todo;
import com.css.autocsfinal.main.service.TodoService;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    // todo리스트 조회
    @GetMapping("/getTodolist")
    public ResponseEntity<ResponseDTO> getTodoList() {

        log.info("[MemberController] selectTodoList start");

        List<TodoDTO> todoList = todoService.getTodo();

        log.info("[MemberController] selectTodoList {}", todoList);
        log.info("[MemberController] selectTodoList end");


        HttpStatus httpStatus = HttpStatus.OK;
        ResponseDTO responseDTO = new ResponseDTO(httpStatus ,"todo 조회" , todoList);
        return ResponseEntity.status(httpStatus).body(responseDTO);

    }


    @GetMapping("/{memberNo}")
    public ResponseEntity<ResponseDTO> getTodoListMember(@PathVariable int memberNo){
        log.info("[MemberController] getTodoMember start");
        log.info("[MemberController] getTodoMember memberNo  {}" , memberNo);
        // memberNO가 1인 사용자의 todo를 가져오는 서비스 메서드 호출
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setMemberNo(memberNo);

        List<TodoDTO> todoList = todoService.getTodoByNo(todoDTO.getMemberNo());

        log.info("[MemberController] getTodoMember {}", todoList);
        log.info("[MemberController] getTodoMember end");

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "memberNO가 1인 사용자의 todo 조회", todoList);

        return ResponseEntity.status(httpStatus).body(responseDTO);
    }


    // 저장하기
    @PostMapping("/insertTodo")
    public ResponseEntity<ResponseDTO> saveTodo(@ModelAttribute TodoDTO todoDTO){


        log.info("====================================================================> {} 등록 테스트", todoDTO);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "등록 성공", todoService.saveTodo(todoDTO)));
    }



}
