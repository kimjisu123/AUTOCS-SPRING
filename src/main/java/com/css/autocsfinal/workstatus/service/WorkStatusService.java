package com.css.autocsfinal.workstatus.service;

import com.css.autocsfinal.workstatus.dto.WorkStatusDTO;
import com.css.autocsfinal.workstatus.entity.WorkStatus;
import com.css.autocsfinal.workstatus.repository.WorkStatusRepsitory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkStatusService {

    private final WorkStatusRepsitory workStatusRepsitory;

    private final ModelMapper modelMapper;

    public Object selectReviewDetail() {

        List<WorkStatus> result = workStatusRepsitory.findAll();
        List<WorkStatusDTO> result2 = result.stream()
                                        .map(WorkStatus -> modelMapper.map(WorkStatus, WorkStatusDTO.class))
                                        .collect(Collectors.toList());

        return result2;
    }
}
