package com.css.autocsfinal.market.repository;

import com.css.autocsfinal.market.entity.StoreInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreInfoRepository extends JpaRepository<StoreInfo, Integer> {

    // 회원 번호로 직원 정보 조회
    @EntityGraph(attributePaths = {"member"})
    StoreInfo findByMemberNo(int memberNo);
}
