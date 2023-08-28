package com.css.autocsfinal.market.repository;

import com.css.autocsfinal.market.entity.ApplyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarketRepository extends JpaRepository<ApplyFile, Long> {

    @Query(value = "SELECT MAX(fileNo) FROM ApplyFile")
    Integer findMaxFileNo();

}
