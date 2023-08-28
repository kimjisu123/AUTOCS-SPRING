package com.css.autocsfinal.market.repository;

import com.css.autocsfinal.market.entity.ApplyFormAndApplyFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketApplyRepository extends JpaRepository<ApplyFormAndApplyFile, Integer> {
}
