package com.css.autocsfinal.mypage.repository;

import com.css.autocsfinal.mypage.entity.MemberFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberFileRepository extends JpaRepository<MemberFile,Integer> {

    MemberFile findByMemberNo(int memberNo);
    MemberFile findById(int memberFileNo);



    @Query("SELECT MAX(m.memberFileNo) FROM MemberFile m WHERE m.member.no = :memberNo")
    Integer findMaxMemberFileNo(@Param("memberNo") int memberNo);

}
