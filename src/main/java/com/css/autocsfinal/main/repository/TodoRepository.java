package com.css.autocsfinal.main.repository;

import com.css.autocsfinal.member.repository.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<MemberRepository,Integer> {

    // 회원번호 조회
}
