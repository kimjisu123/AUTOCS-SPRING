package com.css.autocsfinal.main.service;

import com.css.autocsfinal.main.dto.TodoDTO;
import com.css.autocsfinal.main.entity.Todo;
import com.css.autocsfinal.main.repository.TodoRepository;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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


    // todo목록 조회하기 ( 특정 아이디의 todo리스트를 조회필요) List로 받아야할듯
    @Transactional
    public List<Todo> getTodoListByMemberId(String Id) {

        Member member = memberRepository.findById(Id);
        if(member != null) {
            //TodoDTO todoDTO = modelMapper.map(member.getTodoList(), TodoDTO.class);

            List<Todo> todoList = todoRepository.findByMember(member);

            return todoList;

        }
        return Collections.emptyList();

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
