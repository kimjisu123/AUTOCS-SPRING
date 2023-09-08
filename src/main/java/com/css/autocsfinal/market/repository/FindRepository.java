package com.css.autocsfinal.market.repository;

import com.css.autocsfinal.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FindRepository extends JpaRepository<Member, Integer> {

    String findById(int storeNo);  // 영업점 번호로 조회


}
