package com.css.autocsfinal.mypage.service;


import com.css.autocsfinal.member.repository.EmployeeRepository;
import com.css.autocsfinal.member.repository.MemberRepository;
import com.css.autocsfinal.mypage.repository.MemberFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MypageService {

    public final MemberFileRepository memberFileRepository;
    public final EmployeeRepository employeeRepository;
    public final MemberRepository memberRepository ;

    public final ModelMapper modelMapper;
    public MypageService(MemberFileRepository memberFileRepository, EmployeeRepository employeeRepository, MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberFileRepository = memberFileRepository;
        this.employeeRepository = employeeRepository;
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }


}
