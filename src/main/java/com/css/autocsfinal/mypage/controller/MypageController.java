package com.css.autocsfinal.mypage.controller;


import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.main.dto.TodoDTO;
import com.css.autocsfinal.member.dto.EmployeeAndDepartmentAndPositionDTO;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.mypage.dto.MemberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO;
import com.css.autocsfinal.mypage.dto.MemberFileDTO;
import com.css.autocsfinal.mypage.service.MypageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping
@RestController("/myPage")
public class MypageController {

    private final MypageService mypageService;
    public MypageController(MypageService mypageService) {
        this.mypageService = mypageService;
    }

    // 멤버 정보 불러오기
//    @GetMapping("/{memberNo}")
//    public ResponseEntity<ResponseDTO> getMemberInfo(@PathVariable int memberNo) {
//
//        log.info("[MypageController] getMemberInfo start");
//        log.info("[MypageController] memberNo ===========>{}",memberNo);
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setNo(memberNo);
//
//        MemberFileDTO empInfo = mypageService.getMemberFile(memberDTO.getNo());
//
//        HttpStatus httpStatus = HttpStatus.OK;
//
//        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "memberNO가 1인 사용자의 memberDTO 조회", empInfo);
//
//        return ResponseEntity.status(httpStatus).body(responseDTO);
//
//    }


    // 전체 멤버 정보 불러오기
    @GetMapping("/memberInfo")
    public ResponseEntity<ResponseDTO> getMemberInfo() {

        log.info("[MypageController] getMemberInfo start");

        List<MemberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO> empInfoList = mypageService.getEmployeeFile();

        HttpStatus httpStatus = HttpStatus.OK;
        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "memberDTO 조회", empInfoList);
        return ResponseEntity.status(httpStatus).body(responseDTO);

    }

    // 멤버 정보 수정하기


    // 비밀번호 재확인


    // 비밀 번호 변경하기


    // 멤버 사진 변경하기

//    @PutMapping("/empeimg")
//    public ResponseEntity<ResponseDTO> updateEmpImg(@RequestBody MemberFileDTO memberFileDTO){
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "투두 수정", mypageService.updateImg(memberFileDTO)));
//    }



}
