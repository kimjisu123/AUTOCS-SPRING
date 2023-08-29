package com.css.autocsfinal.mypage.service;


import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.entity.Member;
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


//    public String updateMember(MemberDTO memberDTO){
//
//            Member registMember = modelMapper.map(memberDTO, Member.class);
//
//
//        return '' ;
//    }
//


    // 랜덤한 비밀번호 생성
    //    String newPassword = generateRandomPassword();

    //        log.info("암호화 전 비밀번호 ============================>>>>>>>>>>>>>>>>>>>>>>>>>>> " + newPassword);

    // 비밀번호 암호화
//    String encodedPassword = passwordEncoder.encode(newPassword);


}
