package com.css.autocsfinal.member.service;

import com.css.autocsfinal.member.dto.EmployeeDTO;
import com.css.autocsfinal.member.entity.Employee;
import com.css.autocsfinal.member.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class MemberService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    public MemberService(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
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
}
