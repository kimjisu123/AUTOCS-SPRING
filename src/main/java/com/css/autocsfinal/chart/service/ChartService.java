package com.css.autocsfinal.chart.service;

import com.css.autocsfinal.chart.dto.ChartDTO;
import com.css.autocsfinal.chart.dto.DeptResult;
import com.css.autocsfinal.chart.dto.EmployeeAndPositionDTO;
import com.css.autocsfinal.chart.entity.ChartEntity;
import com.css.autocsfinal.chart.entity.DepartmentEntity;
import com.css.autocsfinal.chart.entity.EmployeeAndPositionEntity;
import com.css.autocsfinal.chart.repository.ChartRepository;
import com.css.autocsfinal.chart.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChartService {

    private final ChartRepository chartRepository;
    private final DepartmentRepository departmentRepository;

    public List<ChartDTO> findChart() {

        log.info("[ChartService] findChart 시작함");
        List<ChartEntity> chartList = chartRepository.findAll();
        log.info("[ChartService] chartList : ", chartList);

        /* 엔티티를 DTO로 변환하는 작업 */
        List<ChartDTO> chartDTOList = chartList.stream().map(chart -> {
            ChartDTO chartDTO = new ChartDTO();

            chartDTO.setName(chart.getName());
            chartDTO.setManagerNo(chart.getManagerNo());
            chartDTO.setEmployeeNo(chart.getEmployeeNo());
            chartDTO.setDepartment(chart.getDepartment().getName());
            chartDTO.setPosition(chart.getPosition().getName());

            return chartDTO;
        }).collect(Collectors.toList());

        return chartDTOList;
    }

    public DeptResult findDept() {

        log.info("[ChartService] DeptResult findDept 실행");
        List<DepartmentEntity> deptList = departmentRepository.findAll();
        log.info("deptList : ", deptList);
        log.info("[ChartService] DeptResult findDept findAll 완료");
        DeptResult result = deptList.stream().map(DeptResult::new).collect(Collectors.toList()).get(deptList.size() - 1);

        return result;
    }

}
