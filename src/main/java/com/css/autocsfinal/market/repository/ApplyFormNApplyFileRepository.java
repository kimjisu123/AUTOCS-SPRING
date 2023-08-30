package com.css.autocsfinal.market.repository;

import com.css.autocsfinal.market.entity.ApplyFormNApplyFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyFormNApplyFileRepository extends JpaRepository<ApplyFormNApplyFile, Integer> {

    ApplyFormNApplyFile findByLicense(String license);
}
