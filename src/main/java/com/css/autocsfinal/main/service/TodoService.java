package com.css.autocsfinal.main.service;

import com.css.autocsfinal.exception.LoginFailedException;
import com.css.autocsfinal.main.dto.TodoDTO;
import com.css.autocsfinal.main.entity.Todo;
import com.css.autocsfinal.main.repository.TodoRepository;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.entity.Employee;
import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

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
    public List<TodoDTO> getTodoByNo(int memberNo) {

        log.info("[TodoService] todo 조회하기 Start ===================");
        List<Todo> todos = todoRepository.findByMemberNo(memberNo);
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
    public String saveTodo(TodoDTO todoDTO) {
        log.info("[TodotService] insertProduct Start ======================================");
        log.info("[TodotService] productDTO : {} " , todoDTO);

        int result = 0; // 결과에 따른 값을 구분하기 위한 용도의 변수

        try{

            Todo insertTodo = modelMapper.map(todoDTO,Todo.class);

            todoRepository.save(insertTodo);
            result=1;

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("[TodotService] insertProduct End ========================================");

        return (result > 0)? " todo 성공" : "todo 실패";


    }






    // todo토글 (완료 상태 변경하기 할일 완료 진행)



    // todo수정하기


    //todo삭제하기



}
