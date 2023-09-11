package com.css.autocsfinal.mypage.controller;


import com.css.autocsfinal.common.ResponseDTO;
import com.css.autocsfinal.main.dto.TodoDTO;
import com.css.autocsfinal.market.dto.StoreInfoDTO;
import com.css.autocsfinal.market.repository.StoreInfoRepository;
import com.css.autocsfinal.member.dto.EmployeeAndDepartmentAndPositionDTO;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.mypage.dto.MemberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO;
import com.css.autocsfinal.mypage.dto.MemberFileDTO;
import com.css.autocsfinal.mypage.service.MypageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;
    public MypageController(MypageService mypageService) {
        this.mypageService = mypageService;
    }



    // 멤버 정보 수정하기
    @PutMapping("/updatememberinfo")
    public ResponseEntity<ResponseDTO> updateTodo(@ModelAttribute EmployeeAndDepartmentAndPositionDTO employeeAndDepartmentAndPositionDTO, MultipartFile fileImage) throws IOException {
        System.out.println("memberFileDTO 컨트롤러에 진입은 했나? = " + employeeAndDepartmentAndPositionDTO);
        log.info("[MypageController]employeeAndDepartmentAndPositionDTO {}", employeeAndDepartmentAndPositionDTO);
        log.info("[MarketController] fileImage {} =======> " + fileImage);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 정보 수정", mypageService.updateMemberInfo(employeeAndDepartmentAndPositionDTO, fileImage)));
    }

    // 매장 정보 수정하기
    @PutMapping("/updatestoreinfo")
    public ResponseEntity<ResponseDTO> updateStore(@ModelAttribute StoreInfoDTO storeInfoDTO) throws IOException {
        System.out.println("storeInfoDTO 컨트롤러에 진입은 했나? = " + storeInfoDTO);
        log.info("[MypageController]storeInfoDTOO {}", storeInfoDTO);
        System.out.println("storeInfoDTO.getAddress() = " + storeInfoDTO.getAddress());
        System.out.println("storeInfoDTO.getEmail() = " + storeInfoDTO.getEmail());
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "매장 정보 수정", mypageService.updateStoreInfo(storeInfoDTO)));
    }



    // 비밀번호 재확인
    @PostMapping("/checkpwd")
    public ResponseEntity<ResponseDTO> checkPwd(@RequestParam("memberNo") int memberNo, @RequestParam("checkpw") String checkPw){
        log.info("[ MypageController ] checkpw {}", checkPw);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "비밀번호 확인", mypageService.checkPwd(memberNo,checkPw)));
    }

    // 비밀 번호 변경하기
    @PutMapping("/changepwd")
    public ResponseEntity<ResponseDTO> changePwd(@RequestBody MemberDTO member){
        log.info("[ MypageController ]  changePwd member.getNo() {}", member.getNo());
        log.info("[ MypageController ]  changePwd member.getPwd() {}", member.getPwd());
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "비밀번호 확인", mypageService.changePwd(member.getNo(),member.getPwd())));
    }


//     멤버 사진 변경하기

    @GetMapping ("/img/{memberNo}")
    public ResponseEntity<ResponseDTO> getEmpImg(@PathVariable int memberNo){
        log.info("[MemberController] getEmpImg start");
        log.info("[MemberController] getEmpImg memberNo  {}" , memberNo);

        MemberFileDTO memberFileDTO = new MemberFileDTO();
        memberFileDTO.setMemberNo(memberNo);
        MemberFileDTO memberFileDTOList = mypageService.getMemberImg(memberFileDTO.getMemberNo());

        log.info("[MemberController] memberFileDTOList  {}" , memberFileDTOList);
        log.info("[MemberController] getEmpImg end");
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseDTO responseDTO = new ResponseDTO(httpStatus, "회원의 프로필사진 조회", memberFileDTOList);
        return ResponseEntity.status(httpStatus).body(responseDTO);
    }





}
