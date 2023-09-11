package com.css.autocsfinal.main;

import com.css.autocsfinal.main.entity.Todo;
import com.css.autocsfinal.member.entity.Member;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.Date;

import static org.testng.AssertJUnit.*;

@Slf4j
public class Test {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;


    @org.testng.annotations.Test
    public void test1(){

        //given
        Todo todo = new Todo();
        Member member = new Member();
        member.setNo(1);
        todo.setMember(member);
        todo.setContent("테스트입니다.");
        todo.setTodoStatus('N');
        todo.setRegDate(LocalDate.now());

        //when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin(); //

        //then
        try {
            entityManager.persist(todo); // 영속성 컨텍스트에 넣고 쿼리문을 작성해서 실행
            entityTransaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            entityTransaction.rollback();
        }

        assertTrue(entityManager.contains(todo));
    }


}
