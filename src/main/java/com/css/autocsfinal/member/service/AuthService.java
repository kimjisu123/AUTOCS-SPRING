package com.css.autocsfinal.member.service;

import com.css.autocsfinal.exception.LoginFailedException;
import com.css.autocsfinal.jwt.TokenProvider;
import com.css.autocsfinal.member.dto.EmployeeAndDepartmentAndPositionDTO;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.dto.TokenDTO;
import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final ModelMapper modelMapper;

    private EmployeeAndDepartmentAndPosition employeeAndDepartmentAndPosition;


    public AuthService(MemberRepository memberRepository
            , PasswordEncoder passwordEncoder
            , TokenProvider tokenProvider
            , ModelMapper modelMapper
    ){
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.modelMapper = modelMapper;
    }

    public TokenDTO login(MemberDTO memberDTO) {

        log.info("[AuthService] login Start ==================================");
        log.info("[AuthService] {} ================== ", memberDTO);
        /* 1. 아이디 조회 */
        Member member = memberRepository.findById(memberDTO.getId());

        log.info("[AuthService] member 조회 {} ================== ", member);

        if(member == null){
            throw new LoginFailedException(memberDTO.getId() + "를 찾을 수 없습니다.");
        }

        /* 2. 비밀번호 매칭
         * passwordEncoder.matches(평문, 다이제스트)
         * */
        if(!passwordEncoder.matches(memberDTO.getPwd(), member.getPwd())){
            log.info("[AuthService] Password Match Fail! ");
            throw new LoginFailedException("잘못된 비밀번호 입니다.");
        }

        //토큰 발급을 위해 member를 담아서 변환
        EmployeeAndDepartmentAndPosition employeeAndDepartmentAndPosition = new EmployeeAndDepartmentAndPosition();
        employeeAndDepartmentAndPosition.setMember(member);

        /* 3. 토큰 발급 */
        TokenDTO tokenDTO = tokenProvider.generateTokenDTO(employeeAndDepartmentAndPosition);
        log.info("[AuthService] tokenDTO {} =======> ", tokenDTO);

        log.info("[AuthService] login End ==================================");

        return tokenDTO;
    }
}
