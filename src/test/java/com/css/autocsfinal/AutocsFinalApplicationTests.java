package com.css.autocsfinal;

import com.css.autocsfinal.main.entity.Todo;
import com.css.autocsfinal.main.repository.TodoRepository;
import com.css.autocsfinal.main.service.TodoService;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AutocsFinalApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private TodoRepository todoRepository;


    @SpringBootTest
    public class TodoServiceTest {

        @Autowired
        private TodoService todoService;

        @Autowired
        private MemberRepository memberRepository;

        @Autowired
        private TodoRepository todoRepository;

        @Test
        public void testGetTodoListByMemberId() {
            // Given
            Member member = new Member();
            member.setId("1");
            memberRepository.save(member);

            Todo todo1 = new Todo();
            todo1.setContent("Task 1");
            todo1.setMember(member);
            todoRepository.save(todo1);

            Todo todo2 = new Todo();
            todo2.setContent("Task 2");
            todo2.setMember(member);
            todoRepository.save(todo2);

            // When
            List<Todo> todoList = todoService.getTodoListByMemberId("1");

            // Then
            assertEquals(2, todoList.size());
        }
    }

}
