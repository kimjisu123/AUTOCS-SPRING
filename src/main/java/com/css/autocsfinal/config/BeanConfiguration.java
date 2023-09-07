package com.css.autocsfinal.config;

import com.css.autocsfinal.market.dto.ApplyFormAndApplyFileDTO;
import com.css.autocsfinal.market.entity.ApplyFormAndApplyFile;
import com.css.autocsfinal.market.repository.MarketRepository;
import com.css.autocsfinal.member.dto.EmployeeDTO;
import com.css.autocsfinal.member.dto.PositionDTO;
import com.css.autocsfinal.member.entity.Employee;
import com.css.autocsfinal.member.entity.Position;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.io.File;
import java.time.LocalDate;


@Configuration
public class BeanConfiguration {

    /* 엔티티와 DTO변환을 위한 modelMapper 빈 설정 */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper;
    }


    /* 암호화 처리를 위한 PasswordEncoder를 빈으로 설정 */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
