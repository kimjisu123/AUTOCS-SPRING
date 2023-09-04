package com.css.autocsfinal;

import com.css.autocsfinal.main.entity.Todo;
import com.css.autocsfinal.main.repository.TodoRepository;
import com.css.autocsfinal.main.service.TodoService;
import com.css.autocsfinal.member.dto.EmployeeAndDepartmentAndPositionDTO;
import com.css.autocsfinal.member.dto.MemberDTO;
import com.css.autocsfinal.member.entity.EmployeeAndDepartmentAndPosition;
import com.css.autocsfinal.member.entity.Member;
import com.css.autocsfinal.member.repository.EmployeeAndDepartmentAndPositionRepository;
import com.css.autocsfinal.member.repository.MemberRepository;
import com.css.autocsfinal.mypage.dto.MemberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO;
import com.css.autocsfinal.mypage.dto.MemberFileDTO;
import com.css.autocsfinal.mypage.entity.MemberAndEmployeeAndDepartmentAndPositionAndMemberFile;
import com.css.autocsfinal.mypage.entity.MemberFile;
import com.css.autocsfinal.mypage.repository.MemberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository;
import com.css.autocsfinal.mypage.repository.MemberFileRepository;
import com.css.autocsfinal.stock.dto.CategoryDTO;
import com.css.autocsfinal.stock.entity.Category;
import com.css.autocsfinal.util.FileUploadUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.Console;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AutocsFinalApplicationTests {

    @Autowired
    private TodoService todoService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MemberFileRepository memberFileRepository;

    @Autowired
    private EmployeeAndDepartmentAndPositionRepository employeeAndDepartmentAndPositionRepository;


    @Autowired
    public MemberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository memberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository;

    @Test
    void contextLoads() {
    }




    /* 이미지 저장 할 위치 및 응답 할 이미지 주소 */
    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;




        @Test
        void test() {
            // 입력테스트
            // Given


            MemberFile memberfile = new MemberFile();
            Member member = new Member();
            member.setNo(41);
            memberfile.setMember(member);
//            Todo todo1 = new Todo();
//            memberfile.setRegDate(new Date());
            memberfile.setOriginName("파일이름");
//            memberfile.setFileNo(2);
            memberFileRepository.save(memberfile);



            // When


            // Then

        }


    @Test
    void test2() {
        //값 변경 테스트
        // Given

        MemberFile memberfile = new MemberFile();
        Member member = new Member();
        EmployeeAndDepartmentAndPosition employeeAndDepartmentAndPosition = new EmployeeAndDepartmentAndPosition();
        member.setNo(100);
        employeeAndDepartmentAndPosition = employeeAndDepartmentAndPositionRepository.findByMemberNo(member.getNo());
//        memberFileRepository.findByMemberNo(member.getNo());
        memberfile = memberFileRepository.findByMemberNo(member.getNo());
        ModelMapper modelMapper = new ModelMapper();
        MemberFileDTO file = modelMapper.map( memberfile, MemberFileDTO.class);


        System.out.println("file = " + file);





        // When


        // Then

    }

    @Test

    void test3(){



        List<MemberAndEmployeeAndDepartmentAndPositionAndMemberFile> empinfoList = memberAndEmployeeAndDepartmentAndPositionAndMemberFileRepository.findAll();

        // Employee 엔티티 리스트를 EmployeeDTO 리스트로 변환하여 반환
        List<MemberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO> employeeDTOList = empinfoList.stream()
                .map(memberAndEmployeeAndDepartmentAndPositionAndMemberFile -> {
                    MemberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO= new MemberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO();

                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setEmployeeNo(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getEmployeeNo());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setName(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getName());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setEmployeeJoin(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getEmployeeJoin());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setDepartment(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getDepartment().getName());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setPosition(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getPosition().getName());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setEmployeeEmail(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getEmployeeEmail());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setMemberNo(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getMember().getNo());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setFileNo(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getMemberFile().getFileNo());
                    memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO.setOriginName(memberAndEmployeeAndDepartmentAndPositionAndMemberFile.getMemberFile().getOriginName());


                    return memberAndEmployeeAndDepartmentAndPositionAndMemberFileDTO;
                })
                .collect(Collectors.toList());



        }



}
