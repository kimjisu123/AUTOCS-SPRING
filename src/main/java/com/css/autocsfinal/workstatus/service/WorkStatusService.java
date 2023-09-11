package com.css.autocsfinal.workstatus.service;

import com.css.autocsfinal.common.Criteria;
import com.css.autocsfinal.member.entity.Employee;
import com.css.autocsfinal.member.repository.EmployeeRepository;
import com.css.autocsfinal.stock.dto.CategoryDTO;
import com.css.autocsfinal.stock.entity.Category;
import com.css.autocsfinal.workstatus.dto.EmployeeAndWorkStatusDTO;
import com.css.autocsfinal.workstatus.dto.WorkStatusAndEmployeeAndDepartmentAndPostionDTO;
import com.css.autocsfinal.workstatus.dto.WorkStatusResult;
import com.css.autocsfinal.workstatus.entity.*;
import com.css.autocsfinal.workstatus.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    // Employee Start
    private final EmployeeAndWorkStatusRepository employeeAndWorkStatusRepository;
    private final EmployeeRepository employeeRepository;



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
        List<WorkStatusResult> workStatusAndEmployeeAndDepartments =
            workStatusAndEmployeeAndDepartmentRepository.findAllWorkStatusResult();

        List<Employee>employeeList = employeeRepository.findAll();

        log.info("total===================================================>{}", employeeList.size());

        for(WorkStatusResult result : workStatusAndEmployeeAndDepartments){
            result.setTotalCount(employeeList.size());
        }

        return workStatusAndEmployeeAndDepartments;

    }

    // 인사부
    public Object findByPersonnel() {

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("인사부");

        List<Employee>employeeList = employeeRepository.findByRefDepartmentCode("H1");

        List<WorkStatusAndEmployeeAndDepartmentAndPostionDTO> resultDTO =  workStatusAndEmployeeAndDepartments.stream().map(workStatusAndEmployeeAndDepartmentAndPostion -> modelMapper.map(workStatusAndEmployeeAndDepartmentAndPostion, WorkStatusAndEmployeeAndDepartmentAndPostionDTO.class)).collect(Collectors.toList());

        for(WorkStatusAndEmployeeAndDepartmentAndPostionDTO result : resultDTO){
            result.setTotalCount(employeeList.size());
        }

        return resultDTO;
    }

    //재무/회계부
    public Object findByAccounting() {

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("재무/회계부");

        List<Employee>employeeList = employeeRepository.findByRefDepartmentCode("F1");

        List<WorkStatusAndEmployeeAndDepartmentAndPostionDTO> resultDTO =  workStatusAndEmployeeAndDepartments.stream().map(workStatusAndEmployeeAndDepartmentAndPostion -> modelMapper.map(workStatusAndEmployeeAndDepartmentAndPostion, WorkStatusAndEmployeeAndDepartmentAndPostionDTO.class)).collect(Collectors.toList());

        for(WorkStatusAndEmployeeAndDepartmentAndPostionDTO result : resultDTO){
            result.setTotalCount(employeeList.size());
        }

        return resultDTO;

    }

    // 경영부
    public Object findByManagement() {

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("경영부");

        List<Employee>employeeList = employeeRepository.findByRefDepartmentCode("M1");

        List<WorkStatusAndEmployeeAndDepartmentAndPostionDTO> resultDTO =  workStatusAndEmployeeAndDepartments.stream().map(workStatusAndEmployeeAndDepartmentAndPostion -> modelMapper.map(workStatusAndEmployeeAndDepartmentAndPostion, WorkStatusAndEmployeeAndDepartmentAndPostionDTO.class)).collect(Collectors.toList());

        for(WorkStatusAndEmployeeAndDepartmentAndPostionDTO result : resultDTO){
            result.setTotalCount(employeeList.size());
        }

        return resultDTO;
    }

    // 마케팅부
    public Object findByMarketing() {

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("마케팅부");

        List<Employee>employeeList = employeeRepository.findByRefDepartmentCode("M2");

        List<WorkStatusAndEmployeeAndDepartmentAndPostionDTO> resultDTO =  workStatusAndEmployeeAndDepartments.stream().map(workStatusAndEmployeeAndDepartmentAndPostion -> modelMapper.map(workStatusAndEmployeeAndDepartmentAndPostion, WorkStatusAndEmployeeAndDepartmentAndPostionDTO.class)).collect(Collectors.toList());

        for(WorkStatusAndEmployeeAndDepartmentAndPostionDTO result : resultDTO){
            result.setTotalCount(employeeList.size());
        }

        return resultDTO;
    }

    // 영업부
    public Object findBySales() {

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("영업부");

        List<Employee>employeeList = employeeRepository.findByRefDepartmentCode("S1");

        List<WorkStatusAndEmployeeAndDepartmentAndPostionDTO> resultDTO =  workStatusAndEmployeeAndDepartments.stream().map(workStatusAndEmployeeAndDepartmentAndPostion -> modelMapper.map(workStatusAndEmployeeAndDepartmentAndPostion, WorkStatusAndEmployeeAndDepartmentAndPostionDTO.class)).collect(Collectors.toList());

        for(WorkStatusAndEmployeeAndDepartmentAndPostionDTO result : resultDTO){
            result.setTotalCount(employeeList.size());
        }

        return resultDTO;
    }

    // 서비스부
    public Object findByService() {

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("서비스부");

        List<Employee>employeeList = employeeRepository.findByRefDepartmentCode("S2");

        List<WorkStatusAndEmployeeAndDepartmentAndPostionDTO> resultDTO =  workStatusAndEmployeeAndDepartments.stream().map(workStatusAndEmployeeAndDepartmentAndPostion -> modelMapper.map(workStatusAndEmployeeAndDepartmentAndPostion, WorkStatusAndEmployeeAndDepartmentAndPostionDTO.class)).collect(Collectors.toList());

        for(WorkStatusAndEmployeeAndDepartmentAndPostionDTO result : resultDTO){
            result.setTotalCount(employeeList.size());
        }

        return resultDTO;
    }

    // 출근
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

    // 퇴근
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


    // 본사 근태관리 조회
    public Object findByHeadOffice(Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count);

        List<EmployeeAndWorkStatus> result = employeeAndWorkStatusRepository.findByOrderByName(paging);

        List<EmployeeAndWorkStatusDTO> employeeAndWorkStatusDTOS =  result.stream().map(employeeAndWorkStatus -> modelMapper.map(employeeAndWorkStatus, EmployeeAndWorkStatusDTO.class)).collect(Collectors.toList());

        log.info("===========================================>{}", employeeAndWorkStatusDTOS);

        return result;
    }


    public int findByHeadOfficeTotal() {

        List<EmployeeAndWorkStatus> data = employeeAndWorkStatusRepository.findByOrderByName();

        return data.size();
    }
}
