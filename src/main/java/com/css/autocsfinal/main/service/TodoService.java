package com.css.autocsfinal.main.service;

import com.css.autocsfinal.exception.LoginFailedException;
import com.css.autocsfinal.main.dto.TodoDTO;
import com.css.autocsfinal.main.entity.Todo;
import com.css.autocsfinal.main.repository.TodoRepository;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TodoService {

    public final MemberRepository memberRepository;
    public final TodoRepository todoRepository;
    public final ModelMapper modelMapper;

    public TodoService(MemberRepository memberRepository, TodoRepository todoRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.todoRepository = todoRepository;
        this.modelMapper = modelMapper;
    }


    // 한 회원 조회하기 ( 특정 아이디의 todo리스트를 조회필요) List로 받아야할듯
    public List<TodoDTO> getTodoListByMember(int memberNo) {
        List<Todo> todos = todoRepository.findByMemberNO(memberNo);
        // todos 엔티티를 TodoDTO로 변환하여 반환하는 로직 구현

        // 예시로 변환 과정을 보여줍니다. 실제로는 실제 변환 로직을 구현해야 합니다.
        List<TodoDTO> todoDTOList =todos.stream()
                .map(todo -> {
                    TodoDTO todoDTO = new TodoDTO();
                    todoDTO.setTodoNo(todo.getTodoNo());
                    todoDTO.setContent(todo.getContent());
                    todoDTO.setTodoStatus(todo.getTodoStatus());
                    todoDTO.setRegDate(new Date(System.currentTimeMillis()));
                    todoDTO.setMemberNo(todo.getMember().getNo());

                    return todoDTO;
                })
                .collect(Collectors.toList());
        return todoDTOList;
    }



// 투두리스트 전체 조회
public List<TodoDTO> getTodo() {
    log.info("[TodoService] todo 조회하기 Start ===================");

    List<Todo> todoList = todoRepository.findAll();
    log.info("todoList : " + todoList);

    // Employee 엔티티 리스트를 EmployeeDTO 리스트로 변환하여 반환
    List<TodoDTO> todoDTOList = todoList.stream()
            .map(todo -> {
                TodoDTO todoDTO = new TodoDTO();
                    todoDTO.setTodoNo(todo.getTodoNo());
                    todoDTO.setContent(todo.getContent());
                    todoDTO.setTodoStatus(todo.getTodoStatus());
                    todoDTO.setRegDate(new Date(System.currentTimeMillis()));
                    todoDTO.setMemberNo(todo.getMember().getNo());

                return todoDTO;
            })
            .collect(Collectors.toList());

    return todoDTOList;
}


    // todo추가하기

    @Transactional
//    public void insertTodo(TodoDTO todoDTO) {
//        log.info("[TodoService] insertTodo start================");
//        log.info("[TodoService] todoDTO {}================", todoDTO);
//        try {
//
//            Todo insertTodo = modelMapper.map(todoDTO,Todo.class);
//            todoRepository.save(insertTodo);
//            log.info("[TodoService] insertTodo success================");
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        log.info("[TodoService] insertTodo end ================");
//
//
//    }

    public Todo add(TodoDTO todoDTO) {
        Todo todo = Todo.builder()
                .todoNo(todoDTO.getTodoNo())
                .content(todoDTO.getContent())
                .todoStatus(todoDTO.getTodoStatus())
//                .regDate(todoDTO.getRegDate(new Date(LocalDate.now()))
//                .upDate(todoDTO.getUpDate())
//                .member(todoDTO.getMemberNo())
                .build();

        return this.todoRepository.save(todo);
    }


    // todo토글 (완료 상태 변경하기 할일 완료 진행)
    public Todo statusTodo(int todoNo){
        return null;
    }



    // todo수정하기


    //todo삭제하기

    public void deleteById(int todoNo){


    }



}
