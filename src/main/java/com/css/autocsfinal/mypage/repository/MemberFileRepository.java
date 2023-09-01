package com.css.autocsfinal.mypage.repository;

import com.css.autocsfinal.mypage.entity.MemberFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFileRepository extends JpaRepository<MemberFile,Integer> {

    MemberFile findByMemberNo(int memberNo);

}
