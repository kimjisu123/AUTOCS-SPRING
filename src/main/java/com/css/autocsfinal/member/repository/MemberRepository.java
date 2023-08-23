package com.css.autocsfinal.member.repository;

import com.css.autocsfinal.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findById(String Id);  // 회원아이디로 조회

    @Query("SELECT MAX(m.no) FROM Member m")
    Integer maxMemberCode();

}
