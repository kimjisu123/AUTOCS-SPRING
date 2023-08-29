package com.css.autocsfinal.main.controller;


import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.main.dto.TodoDTO;
import com.css.autocsfinal.main.entity.Todo;
import com.css.autocsfinal.main.service.TodoService;
import com.css.autocsfinal.member.service.MemberService;
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
    @GetMapping("/{id}")
    public ResponseEntity<?> selectTodoList(@PathVariable String id) {

        log.info("[MemberController] selectTodoList start");

        List<Todo> todoList = todoService.getTodoListByMemberId(id);

        log.info("[MemberController] selectTodoList {}", todoList);
        log.info("[MemberController] selectTodoList end");

        // 회원번호로 todo리스트 조회
        return ResponseEntity.ok().body(todoList);

    }


    // INSERT
//    @PostMapping
//    public ResponseEntity<?> addTodoItem(@RequestBody TodoDTO todoDTO, BindingResult bindingResult) {
//
//       // todoService.insertTodo(todoDTO);
//        return new ResponseEntity<>("추가되었습니다.", HttpStatus.OK);
//
//
//    }

    // UPDATE
//    @PutMapping("/{no}")
//    public ResponseEntity<?> modifyTodo() {
//
//        return new ResponseEntity<>('a',HttpStatus.OK);
//    }

    // DELETE

//    @DeleteMapping("/{no")
//    public ResponseEntity<?> deleteTodo() {
//
//        return new ResponseEntity<>("g",HttpStatus.OK);
//    }

}
