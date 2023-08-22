package com.css.autocsfinal.config;

import com.css.autocsfinal.member.dto.EmployeeDTO;
import com.css.autocsfinal.member.entity.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class BeanConfiguration {
    /* 엔티티와 DTO변환을 위한 modelMapper 빈 설정 */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

         //EmployeeDTO -> Employee 매핑 설정
        TypeMap<EmployeeDTO, Employee> employeeDtoToEmployeeMap = modelMapper.createTypeMap(EmployeeDTO.class, Employee.class);
        employeeDtoToEmployeeMap.addMapping(EmployeeDTO::getName, Employee::setName);
        employeeDtoToEmployeeMap.addMapping(EmployeeDTO::getDepartmentCode, Employee::setDepartmentCode);
        employeeDtoToEmployeeMap.addMapping(EmployeeDTO::getPositionCode, Employee::setPositionCode);
        employeeDtoToEmployeeMap.addMapping(EmployeeDTO::getEmployeeJoin, Employee::setEmployeeJoin);

        return modelMapper;
    }


    /* 암호화 처리를 위한 PasswordEncoder를 빈으로 설정 */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
