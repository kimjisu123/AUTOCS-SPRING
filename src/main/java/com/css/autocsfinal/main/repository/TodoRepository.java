package com.css.autocsfinal.main.repository;

import com.css.autocsfinal.main.entity.Todo;
import com.css.autocsfinal.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface TodoRepository extends JpaRepository<Todo,Integer> {

    // 회원번호 조회
    List<Todo> findByMember(Member member);

//    Todo findByTodoNo(String memberNo);

}
