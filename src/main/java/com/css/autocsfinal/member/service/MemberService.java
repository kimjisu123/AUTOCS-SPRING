package com.css.autocsfinal.member.service;

import com.css.autocsfinal.member.dto.EmployeeAndDepartmentAndPositionDTO;
import com.css.autocsfinal.member.dto.EmployeeDTO;
import com.css.autocsfinal.member.entity.Employee;
import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import com.css.autocsfinal.member.repository.EmployeeAndDepartmentAndPositionRepository;
import com.css.autocsfinal.member.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MemberService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    private final EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository;

    public MemberService(ModelMapper modelMapper, EmployeeRepository employeeRepository, EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
        this.employeeAndDepartmentAndPositionRepository = employeeAndDepartmentAndPositionRepository;
    }

    @Transactional
    public String insertEmployee(EmployeeDTO employeeDTO) {
        log.info("[MemberService] 사원 등록 Start ===================");
        log.info("[MemberService] employeeDTO : " + employeeDTO);

        Employee registEmployee = modelMapper.map(employeeDTO, Employee.class);

        Employee savedEmployee = employeeRepository.save(registEmployee);

        log.info("[MemberService] insertEmployee End ===================");
        return (savedEmployee != null) ? "사원 등록 성공" : "사원 등록 실패";
    }

    public List<EmployeeDTO> getEmployee() {
        log.info("[MemberService] 사원 조회 Start ===================");

        List<Employee> employeeList = employeeRepository.findAll();
        log.info("employeeList : " + employeeList);

        // Employee 엔티티 리스트를 EmployeeDTO 리스트로 변환하여 반환
        List<EmployeeDTO> employeeDTOList = employeeList.stream()
                .map(employee -> {
                    EmployeeDTO employeeDTO = new EmployeeDTO();

                    employeeDTO.setEmployeeNo(employee.getEmployeeNo());
                    employeeDTO.setName(employee.getName());
                    employeeDTO.setEmployeeJoin(employee.getEmployeeJoin());


                    return employeeDTO;
                })
                .collect(Collectors.toList());

        return employeeDTOList;
    }

}
