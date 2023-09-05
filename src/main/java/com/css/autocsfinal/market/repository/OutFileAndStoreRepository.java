package com.css.autocsfinal.market.repository;

import com.css.autocsfinal.market.entity.OutFile;
import com.css.autocsfinal.market.entity.StoreAndOut;
import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OutFileAndStoreRepository extends JpaRepository<StoreAndOut, Integer> {

    //영업점 전체 조회
    @Query("SELECT s FROM StoreAndOut s RIGHT JOIN FETCH s.store WHERE s.fileNo IS NOT NULL")
    List<StoreAndOut> findAll();

}
