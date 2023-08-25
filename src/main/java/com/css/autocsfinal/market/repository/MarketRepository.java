package com.css.autocsfinal.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.css.autocsfinal.market.entity.ApplyFormAndApplyFile;

public interface MarketRepository extends JpaRepository<ApplyFormAndApplyFile, Long> {

}
