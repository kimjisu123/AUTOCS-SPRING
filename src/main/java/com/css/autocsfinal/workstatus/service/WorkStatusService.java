package com.css.autocsfinal.workstatus.service;

import com.css.autocsfinal.workstatus.dto.WorkStatusDTO;
import com.css.autocsfinal.workstatus.entity.WorkStatus;
import com.css.autocsfinal.workstatus.repository.WorkStatusRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkStatusService {

    private final WorkStatusRepsitory workStatusRepsitory;

    public Object selectReviewDetail() {

        List<WorkStatus> result = workStatusRepsitory.findAll();

        return result;
    }
}
