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

import java.time.LocalDate;
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

    // 근태 현황 조회
    public Object findByEmployeeNo(int employeeNo) {

        List<WorkStatusList> workStatusList = workStatusListRepository.findByEmployeeNoOrderByWorkStatusCodeDesc(employeeNo);

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

        log.info("=====================================>{}", resultDTO.size());

        return resultDTO;
    }

    // 인사부 검색어
    public Object findByPersonnel(String name) {


        name = '%'+name +'%';

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("인사부", name);

        List<Employee>employeeList = employeeRepository.findByRefDepartmentCode("H1");

        List<WorkStatusAndEmployeeAndDepartmentAndPostionDTO> resultDTO =  workStatusAndEmployeeAndDepartments.stream().map(workStatusAndEmployeeAndDepartmentAndPostion -> modelMapper.map(workStatusAndEmployeeAndDepartmentAndPostion, WorkStatusAndEmployeeAndDepartmentAndPostionDTO.class)).collect(Collectors.toList());

        for(WorkStatusAndEmployeeAndDepartmentAndPostionDTO result : resultDTO){
            result.setTotalCount(employeeList.size());
        }

        log.info("==========================================>{}", resultDTO.size());

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
    // 재무 회계 검색용
    public Object findByAccounting(String name) {

        name = '%'+name +'%';

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("재무/회계부", name);

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

    // 경영부 검색
    public Object findByManagement(String name) {

        name = '%'+name +'%';

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("경영부", name);

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

        log.info("============Test===========>{}", resultDTO);

        return resultDTO;
    }

    // 마케팅부 검색어
    public Object findByMarketing(String name) {

        name = '%'+name +'%';

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("마케팅부", name);

        List<Employee>employeeList = employeeRepository.findByRefDepartmentCode("M2");


        List<WorkStatusAndEmployeeAndDepartmentAndPostionDTO> resultDTO =  workStatusAndEmployeeAndDepartments.stream().map(workStatusAndEmployeeAndDepartmentAndPostion -> modelMapper.map(workStatusAndEmployeeAndDepartmentAndPostion, WorkStatusAndEmployeeAndDepartmentAndPostionDTO.class)).collect(Collectors.toList());

        for(WorkStatusAndEmployeeAndDepartmentAndPostionDTO result : resultDTO){
            result.setTotalCount(employeeList.size());
        }

        log.info("============Test===========>{}", resultDTO);

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

    // 영업부 검색
    public Object findBySales(String name) {

        name = '%'+name +'%';

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("영업부", name);

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

        log.info("=============================>{}",resultDTO);

        return resultDTO;
    }

    // 서비스부 검색
    public Object findByService(String name) {

        name = '%'+name +'%';

        List<WorkStatusAndEmployeeAndDepartmentAndPostion> workStatusAndEmployeeAndDepartments =
                workStatusAndEmployeeAndDepartmentRepository.findByDepartmentName("서비스부", name);



        List<Employee>employeeList = employeeRepository.findByRefDepartmentCode("S2");

        List<WorkStatusAndEmployeeAndDepartmentAndPostionDTO> resultDTO =  workStatusAndEmployeeAndDepartments.stream().map(workStatusAndEmployeeAndDepartmentAndPostion -> modelMapper.map(workStatusAndEmployeeAndDepartmentAndPostion, WorkStatusAndEmployeeAndDepartmentAndPostionDTO.class)).collect(Collectors.toList());

        for(WorkStatusAndEmployeeAndDepartmentAndPostionDTO result : resultDTO){
            result.setTotalCount(employeeList.size());
        }

        log.info("=============================>{}",resultDTO);

        return resultDTO;
    }

    // 출근
    @Transactional
    public void saveAttendance(int employeeNo) {

        WorkStatus workStatus =new WorkStatus();

        workStatus.setAttendanceTime(new Date());
        workStatus.setVacationStatus('N');
        workStatus.setAbsenceWorkStatus('N');

        // 결과에 따른 예외 처리를 위한 용도의 변수
        int result = 0;

        // 오늘 출근 기록 조회
        int check = workStatusListRepository.countByAttendanceTime(employeeNo);

        // 출근 기록이 없을 경우 출근
        if(check == 0){

            WorkStatus resultStatus = workStatusRepsitory.save(workStatus);

            int resultCode = resultStatus.getWorkStatusCode();

            WorkStatusList workStatusList = new WorkStatusList(employeeNo, resultCode);

            workStatusListRepository.save(workStatusList);

            result = 1;
        }

        if(result == 0){
            throw new RuntimeException("출근 등록 실패");
        }

    }

    // 퇴근
    @Transactional
    public void saveQuitting(int employeeNo) {

        // 결과에 따른 예외 처리를 위한 용도의 변수
        int result = 0;

        // 해당 직원에 대한 출근 리스트 조회
        List<WorkStatusList> workStatusLists = workStatusListRepository.findByEmployeeNo(employeeNo);

        // 오늘 출근 기록 조회
        int check = workStatusListRepository.countByAttendanceTime(employeeNo);

        // 오늘 퇴근 기록 조회
        int check2 = workStatusListRepository.countByquittingTime(employeeNo);

        // 오늘 출근 기록이 있고 퇴근 기록이 없을 경우 조회
        if(check != 0 && check2 ==0){

            // 시퀀스로 인해 마지막에 있는 출근 코드가 가장 최근의 근태코드인 오늘의 근태코드를 가져온다.
            int workStatusCode = 0;
            for(int i = 0; i < workStatusLists.size(); i++){
                workStatusCode =  workStatusLists.get(i).getWorkStatusCode();
            }

            // 오늘의 근태코드로 Entity객체 생성
            WorkStatus workStatus = workStatusRepsitory.findByWorkStatusCode(workStatusCode);

            // 퇴근 시간 업데이트
            workStatus.setQuittingTime(new Date());

            Long dateTime = workStatus.getQuittingTime().getTime() - workStatus.getAttendanceTime().getTime();

            workStatus.setExtensionTime(new Date(dateTime));

            // 퇴근 수정 작업이 성공한 경우
            result = 1;
        }

        // 퇴근 수정 작업이 실패한 경우 예외 처리
        if (result == 0) {
            throw new RuntimeException("퇴근 수정 실패");
        }
    }


    // 본사 근태관리 조회
    public Object findByHeadOffice(Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count);

        List<EmployeeAndWorkStatus> result = employeeAndWorkStatusRepository.findByOrderByName(paging);

        List<EmployeeAndWorkStatusDTO> employeeAndWorkStatusDTOS =  result.stream().map(employeeAndWorkStatus -> modelMapper.map(employeeAndWorkStatus, EmployeeAndWorkStatusDTO.class)).collect(Collectors.toList());

        return employeeAndWorkStatusDTOS;
    }

    // 본사 근태관리 조회, 검색, 페이징 처리
    public Object findByHeadOffice(Criteria cri, String name) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count);

        List<EmployeeAndWorkStatus> result = employeeAndWorkStatusRepository.findByOrderByName(paging, name);

        List<EmployeeAndWorkStatusDTO> employeeAndWorkStatusDTOS =  result.stream().map(employeeAndWorkStatus ->
                                                                                            modelMapper.map(employeeAndWorkStatus, EmployeeAndWorkStatusDTO.class))
                                                                                            .collect(Collectors.toList());

        return employeeAndWorkStatusDTOS;
    }


    public int findByHeadOfficeTotal() {

        List<EmployeeAndWorkStatus> data = employeeAndWorkStatusRepository.findByOrderByName();

        return data.size();
    }

    public int findByHeadOfficeTotal(String name) {

        List<EmployeeAndWorkStatus> data = employeeAndWorkStatusRepository.findByOrderByName(name);

        return data.size();
    }
}
