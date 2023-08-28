package com.css.autocsfinal.main.controller;


import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.main.entity.Todo;
import com.css.autocsfinal.main.service.TodoService;
import com.css.autocsfinal.member.service.MemberService;
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
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    // todo리스트 조회
    @GetMapping("/{id}/todos")
    public ResponseEntity<List<Todo>> selectTodoList(@PathVariable String id) {

        List<Todo> todoList = todoService.getTodoListByMemberId(id);
        log.info("[MemberController] selectTodoList start");
        log.info("[MemberController] selectTodoList {}", todoList);
        log.info("[MemberController] selectTodoList end");

        return ResponseEntity.ok().body(todoList);

    }

    // todo리스트 insert



}
