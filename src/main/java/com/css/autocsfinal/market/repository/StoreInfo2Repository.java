package com.css.autocsfinal.market.repository;

import com.css.autocsfinal.market.entity.StoreInfo;
import com.css.autocsfinal.market.entity.StoreInfo2;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreInfo2Repository extends JpaRepository<StoreInfo2, Integer> {

    //영업점 조회
    StoreInfo2 findByMemberNo(int memberNo);
}
