package com.css.autocsfinal.workstatus.controller;

import com.css.autocsfinal.common.*;
import com.css.autocsfinal.workstatus.dto.WorkStatusDTO;
import com.css.autocsfinal.workstatus.repository.WorkStatusListRepository;
import com.css.autocsfinal.workstatus.service.WorkStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class WorkStatusController {

    private final WorkStatusService workStatusService;

    @GetMapping("/workStatus/{employeeNo}")
    public ResponseEntity<ResponseDTO> findByAll(@PathVariable int employeeNo){

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "근태 관리 조회 성공",  workStatusService.findByEmployeeNo(employeeNo)));
    }

    @GetMapping("/department")
    public ResponseEntity<ResponseDTO> findByDepartmentAll(){

        log.info("============================?>>");
        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "본사 근태 관리 조회 성공", workStatusService.findByDepartmentAll()));
    }

    // 인사부 조회
    @GetMapping("/personnel/{name}")
    public ResponseEntity<ResponseDTO> findByPersonnel(@PathVariable String name){

        Object object;

        if(name.equals("절대로아무도검색하지않을만한값입니다.")){
            object = workStatusService.findByPersonnel();
        } else{
            object = workStatusService.findByPersonnel(name);
        }

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "인사부 조회 성공", object));
    }

    // 재무/회계부 조회
    @GetMapping("/accounting/{name}")
    public ResponseEntity<ResponseDTO> findByAccounting(@PathVariable String name){

        Object object;

        if(name.equals("절대로아무도검색하지않을만한값입니다.")){
            object = workStatusService.findByAccounting();
        } else{
            log.info("============================>22222222222222222222222222222");
            object = workStatusService.findByAccounting(name);
        }

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "재무/회계부 조회 성공", object));
    }

    // 경영부 조회
    @GetMapping("/management/{name}")
    public ResponseEntity<ResponseDTO> findByManagement(@PathVariable String name){

        Object object;

        if(name.equals("절대로아무도검색하지않을만한값입니다.")){
            log.info("============================>111111111111111111111111");
            object = workStatusService.findByManagement();
        } else{
            log.info("============================>22222222222222222222222222222");
            object = workStatusService.findByManagement(name);
        }


        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "경영부 조회 성공", object ));
    }

    // 마케팅부 조회
    @GetMapping("/marketing/{name}")
    public ResponseEntity<ResponseDTO> findByMarketing(@PathVariable String name){

        Object object;

        if(name.equals("절대로아무도검색하지않을만한값입니다.")){
            log.info("============================>111111111111111111111111");
            object = workStatusService.findByMarketing();
        } else{
            log.info("============================>22222222222222222222222222222");
            object = workStatusService.findByMarketing(name);
        }



        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "마케팅부 조회 성공", object ));
    }

    // 영업부 조회
    @GetMapping("/sales/{name}")
    public ResponseEntity<ResponseDTO> findBySales(@PathVariable String name){

        Object object;

        if(name.equals("절대로아무도검색하지않을만한값입니다.")){
            log.info("============================>111111111111111111111111");
            object = workStatusService.findBySales();
        } else{
            log.info("============================>22222222222222222222222222222");
            object = workStatusService.findBySales(name);
        }
        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "영업부 조회 성공", object));
    }

    // 서비스부 조회
    @GetMapping("/service/{name}")
    public ResponseEntity<ResponseDTO> findByService(@PathVariable String name){

        Object object;

        if(name.equals("절대로아무도검색하지않을만한값입니다.")){
            log.info("============================>111111111111111111111111");
            object = workStatusService.findByService();
        } else{
            log.info("============================>22222222222222222222222222222");
            object = workStatusService.findByService(name);
        }



        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "서비스부 조회 성공", object));
    }

    // 출근하기
    @PostMapping("/attendance/{employeeNo}")
    public ResponseEntity<ResponseDTO> saveAttendance(@PathVariable int employeeNo ){


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "출근 성공", workStatusService.saveAttendance(employeeNo)));
    }

    // 퇴근
    @PutMapping("/quitting/{employeeNo}")
    public ResponseEntity<ResponseDTO> saveQuitting(@PathVariable int employeeNo){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "퇴근 성공", workStatusService.saveQuitting(employeeNo)));
    }

    // 본사 근태 현황

    @GetMapping("/headOffice/{page}/{search}")
    public ResponseEntity<ResponseDTO> findByHeadOffice( @PathVariable(name = "page", required = false) int offset, @PathVariable(name = "search", required = false) String name){


        int total;


        Criteria cri = new Criteria(Integer.valueOf(offset), 8);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        if(name.equals("절대로아무도검색하지않을만한값입니다.")){
            total = workStatusService.findByHeadOfficeTotal();
            pagingResponseDTO.setData(workStatusService.findByHeadOffice(cri));
        } else {
            total = workStatusService.findByHeadOfficeTotal(name);
            pagingResponseDTO.setData(workStatusService.findByHeadOffice(cri, name));
        }

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "본사 근태관리 조회 성공", pagingResponseDTO));
    }
}
























