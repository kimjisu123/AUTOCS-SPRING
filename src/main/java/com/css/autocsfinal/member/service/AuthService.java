package com.css.autocsfinal.member.service;

import com.css.autocsfinal.exception.LoginFailedException;
import com.css.autocsfinal.jwt.TokenProvider;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.dto.TokenDTO;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;
import javax.transaction.Transactional;

@Service
@Slf4j
public class AuthService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final ModelMapper modelMapper;


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

        /* 3. 토큰 발급 */
        TokenDTO tokenDTO = tokenProvider.generateTokenDTO(member);
        log.info("[AuthService] tokenDTO {} =======> ", tokenDTO);

        log.info("[AuthService] login End ==================================");

        return tokenDTO;
    }

    @Transactional
    public Object signupMarket(MemberDTO memberDTO) {
        log.info("[AuthService] 영업점 생성 Start ==================================");
        log.info("[AuthService] memberDTO {} =======> ", memberDTO);

        /* check
         * 값을 받은건 MemberDTO클래스이다. 여기 담긴 값을 repository를 통해서 쿼리를 요청해야한다.
         * 그래서 현재 MemberDTO객체를 entity 객체인 Member로 변경해주는 작업 필요하다. */
        Member registMember = modelMapper.map(memberDTO, Member.class);

        // 아이디 생성
        int nextMemberCode = memberRepository.maxMemberCode() + 1;
        String newUserId = "Market" + nextMemberCode;

        // 랜덤한 비밀번호 생성
        String newPassword = generateRandomPassword();

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(newPassword);

        registMember.setId(newUserId);
        registMember.setPwd(encodedPassword);
        registMember.setRole("영업점");

        Member result = memberRepository.save(registMember);

        log.info("[AuthService] MemberInsert Result {}",
                (result != null) ? "계정 생성 성공" : "계정 생성 실패");

        return tokenProvider.generateTokenDTO(result);
    }

    private String generateRandomPassword() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
