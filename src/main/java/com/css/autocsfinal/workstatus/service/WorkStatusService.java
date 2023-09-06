package com.css.autocsfinal.workstatus.service;

import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.EmployeeRepository;
import com.css.autocsfinal.member.repository.MemberRepository;
import com.css.autocsfinal.workstatus.dto.EmployeeByWorkStatusDTO;
import com.css.autocsfinal.workstatus.dto.WorkStatusResult;
import com.css.autocsfinal.workstatus.entity.EmployeeByWorkStatus;
import com.css.autocsfinal.workstatus.entity.WorkStatus;
import com.css.autocsfinal.workstatus.entity.WorkStatusAndEmployeeAndDepartmentAndPostion;
import com.css.autocsfinal.workstatus.entity.WorkStatusList;
import com.css.autocsfinal.workstatus.repository.EmployeeByWorkStatusRepository;
import com.css.autocsfinal.workstatus.repository.WorkStatusAndEmployeeAndDepartmentRepository;
import com.css.autocsfinal.workstatus.repository.WorkStatusListRepository;
import com.css.autocsfinal.workstatus.repository.WorkStatusRepsitory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkStatusService {

    private final WorkStatusRepsitory workStatusRepsitory;
    private final WorkStatusListRepository workStatusListRepository;
    private final WorkStatusAndEmployeeAndDepartmentRepository workStatusAndEmployeeAndDepartmentRepository;
    private final EmployeeByWorkStatusRepository employeeByWorkStatusRepository;


    private final ModelMapper modelMapper;

    public Object findByEmployeeNo(int employeeNo) {


        List<WorkStatusList> workStatusList = workStatusListRepository.findByEmployeeNoOrderByWorkStatusCode(employeeNo);

        List<WorkStatus> workStatuses = new ArrayList<>();

        for(int i=0; i<workStatusList.size(); i++){

            workStatuses.add(workStatusList.get(i).getWorkStatus()) ;
        }





        return workStatuses;
    }

    // 본사 근태관리 조회
    public Object findByDepartmentAll() {
        log.info("=====================11111111111111111111111111111111");
        List<WorkStatusResult> workStatusAndEmployeeAndDepartments =
            workStatusAndEmployeeAndDepartmentRepository.findAllWorkStatusResult();

        log.info("=====================222222222222222222222222222222222222");
        return workStatusAndEmployeeAndDepartments;

    }

    // 인사부
    public Object findByPersonnel() {

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("인사부");

        return workStatusAndEmployeeAndDepartments;
    }

    //재무/회계부
    public Object findByAccounting() {

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("재무/회계부");

        return workStatusAndEmployeeAndDepartments;

    }

    // 경영부
    public Object findByManagement() {

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("경영부");

        return workStatusAndEmployeeAndDepartments;
    }

    // 마케팅부
    public Object findByMarketing() {

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("마케팅부");

        return workStatusAndEmployeeAndDepartments;
    }

    // 영업부
    public Object findBySales() {

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("영업부");

        return workStatusAndEmployeeAndDepartments;
    }

    // 서비스부
    public Object findByService() {

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("서비스부");

        return workStatusAndEmployeeAndDepartments;
    }

    @Transactional
    public Object saveAttendance(int employeeNo) {

        WorkStatus workStatus =new WorkStatus();

        workStatus.setAttendanceTime(new Date());
        workStatus.setVacationStatus('N');
        workStatus.setAbsenceWorkStatus('N');

        WorkStatus result = workStatusRepsitory.save(workStatus);

        int statusCode = result.getWorkStatusCode();

        WorkStatusList workStatusList = new WorkStatusList(employeeNo, statusCode);

        WorkStatusList listResult = workStatusListRepository.save(workStatusList);

        return null;
    }

    @Transactional
    public Object saveQuitting() {

        Optional <WorkStatus> workStatus = workStatusRepsitory.findTopByOrderByAttendanceTimeDesc();

        WorkStatus result = new WorkStatus();


        if (workStatus.isPresent()) {
            WorkStatus  recentlyTime = workStatus.get();
            recentlyTime.setQuittingTime(new Date());
            result = workStatusRepsitory.save(recentlyTime);
        }


        Long resultTime = ( result.getQuittingTime().getTime() - result.getAttendanceTime().getTime() );


        Date extensionTime = new Date(resultTime);


        result.setExtensionTime(extensionTime);


        return result;

    }



}
