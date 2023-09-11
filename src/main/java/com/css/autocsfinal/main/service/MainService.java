package com.css.autocsfinal.main.service;

import com.css.autocsfinal.schedule.entity.Schedule;
import com.css.autocsfinal.schedule.entity.ScheduleDTO;
import com.css.autocsfinal.schedule.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MainService {

    public final ScheduleRepository scheduleRepository;

    public MainService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<ScheduleDTO> getScheduleByOne(int memberNo) {

        int num = 240;
        List<Schedule> schedules = scheduleRepository.findByMemberNo(memberNo);
        List<ScheduleDTO> sclist = schedules.stream().map(schedule -> {
            ScheduleDTO sc = new ScheduleDTO();
            sc.setName(schedule.getName());
            sc.setScheduleCode(schedule.getScheduleCode());
            sc.setContent(schedule.getContent());
            sc.setStartDate(schedule.getStartDate());
            sc.setMemberNo(schedule.getMember().getNo());
            return sc;
        }).collect(Collectors.toList());

        System.out.println("schedule = " + sclist);

        return sclist;
    }

}
