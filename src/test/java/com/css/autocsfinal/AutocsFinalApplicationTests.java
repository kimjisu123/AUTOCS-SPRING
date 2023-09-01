package com.css.autocsfinal;

import com.css.autocsfinal.main.entity.Todo;
import com.css.autocsfinal.main.repository.TodoRepository;
import com.css.autocsfinal.main.service.TodoService;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AutocsFinalApplicationTests {

    @Autowired
    private TodoService todoService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TodoRepository todoRepository;
    @Test
    void contextLoads() {
    }








        @Test
        void test() {
            // 입력테스트
            // Given

            Member member = new Member();
            member.setNo(61);
            Todo todo1 = new Todo();
            todo1.setContent("Task 1");
            todo1.setMember(member);
            todoRepository.save(todo1);



            // When


            // Then

        }


    @Test
    void test2() {
        //값 변경 테스트
        // Given

//        Member member = new Member();
//        member.setNo(61);
//        Todo todo1 = new Todo();
//        todo1.setTodoNo(217);
//        todo1.setTodoStatus('Y');
//        todo1.setMember(member);
//        todoRepository.findByMemberNo(member.getNo());
//        todoRepository.notifyAll();



        // When


        // Then

    }




}
