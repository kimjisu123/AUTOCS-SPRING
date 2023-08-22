package com.css.autocsfinal.workstatus.service;

import com.css.autocsfinal.workstatus.dto.WorkStatusDTO;
import com.css.autocsfinal.workstatus.entity.WorkStatus;
import com.css.autocsfinal.workstatus.repository.WorkStatusRepsitory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkStatusService {

    private final WorkStatusRepsitory workStatusRepsitory;

    public Object selectReviewDetail() {

        List<WorkStatus> result = workStatusRepsitory.findAll();

        log.info("result ==== " + result);

        return result;
    }
}
