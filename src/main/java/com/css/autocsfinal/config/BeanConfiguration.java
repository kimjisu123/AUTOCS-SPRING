package com.css.autocsfinal.config;

import com.css.autocsfinal.market.dto.ApplyFormAndApplyFileDTO;
import com.css.autocsfinal.market.entity.ApplyFormAndApplyFile;
import com.css.autocsfinal.member.dto.EmployeeDTO;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.entity.Employee;
import com.css.autocsfinal.member.entity.Member;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.time.LocalDate;


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
        //이거 조인해서 가져와야함(코드이름)
        //employeeDtoToEmployeeMap.addMapping(EmployeeDTO::getPositionCode, Employee::getPosition.setCode);
        employeeDtoToEmployeeMap.addMapping(EmployeeDTO::getEmployeeJoin, Employee::setEmployeeJoin);
        //이거 조인해서 가져와야함(상사코드)
        //employeeDtoToEmployeeMap.addMapping(EmployeeDTO::getUpCode, Employee::getPosition.setUpCode);

//        // ApplyFormAndApplyFileDTO -> ApplyFormAndApplyFile 매핑 설정
//        TypeMap<ApplyFormAndApplyFileDTO, ApplyFormAndApplyFile> applyFormMapping = modelMapper.createTypeMap(ApplyFormAndApplyFileDTO.class, ApplyFormAndApplyFile.class);
//        // 명시적인 속성 매핑 추가
//        //주소
//        applyFormMapping.addMapping(ApplyFormAndApplyFileDTO::getAddress, ApplyFormAndApplyFile::setAddress);
//        //대표자명
//        applyFormMapping.addMapping(ApplyFormAndApplyFileDTO::getName, ApplyFormAndApplyFile::setName);
//        //사업자등록번호
//        applyFormMapping.addMapping(ApplyFormAndApplyFileDTO::getLicense, ApplyFormAndApplyFile::setLicense);
//        //이메일
//        applyFormMapping.addMapping(ApplyFormAndApplyFileDTO::getEmail, ApplyFormAndApplyFile::setEmail);
//        //신청 상태
//        applyFormMapping.addMapping(ApplyFormAndApplyFileDTO::getState, (destination, value) -> {
//            destination.setState("W");
//        });
//
//        //파일 넘버
//        applyFormMapping.addMapping(ApplyFormAndApplyFileDTO::getRefAppleNo, (destination, value) -> {
//            destination.getFile().setFileNo(0);
//        });
//        //파일 이름
//        Converter<String, File> stringToFileConverter = context -> new File(context.getSource());
//        applyFormMapping.addMappings(mapper -> {
//            mapper.using(stringToFileConverter).map(ApplyFormAndApplyFileDTO::getOriginal, ApplyFormAndApplyFile::setFile);
//        });
//        //파일 변경명
//        applyFormMapping.addMapping(ApplyFormAndApplyFileDTO::getChange, (destination, value) -> {
//            destination.getFile().setChange("변경합니다.");
//        });
//        //파일 등록일
//        applyFormMapping.addMapping(ApplyFormAndApplyFileDTO::getRegistDate, (destination, value) -> {
//            destination.getFile().setRegistDate(LocalDate.now());
//        });
//        //신청 종류
//        applyFormMapping.addMapping(ApplyFormAndApplyFileDTO::getKind, (destination, value) -> {
//            destination.getFile().setKine("표준가맹계약서");
//        });

        return modelMapper;
    }


    /* 암호화 처리를 위한 PasswordEncoder를 빈으로 설정 */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
