package com.css.autocsfinal.workstatus.service;

import com.css.autocsfinal.workstatus.dto.WorkStatusDTO;
import com.css.autocsfinal.workstatus.entity.WorkStatus;
import com.css.autocsfinal.workstatus.repository.WorkStatusRepsitory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkStatusService {

    private final WorkStatusRepsitory workStatusRepsitory;

    private final ModelMapper modelMapper;

    public Object selectReviewDetail() {

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//
//        WorkStatus workStatus = new WorkStatus(3, new Date(), new Date(), 'N', 'N', "없앨까" , new Date());
//
//        workStatusRepsitory.save(workStatus);
//
//
//
//        log.info("==========================================> findAll start");

        List<WorkStatus> workStatusList = workStatusRepsitory.findAll();


        log.info("==========================================> findAll end {}", workStatusList);

        return workStatusList;
    }
}
