package com.css.autocsfinal.market.repository;

import com.css.autocsfinal.market.entity.OutFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutFileRepository extends JpaRepository<OutFile, Integer> {

    OutFile findByFileNo(int fileNo);
}
