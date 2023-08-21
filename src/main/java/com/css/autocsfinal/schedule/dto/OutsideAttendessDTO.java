package com.css.autocsfinal.schedule.entity;

import lombok.*;

import javax.annotation.Generated;
import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OutsideAttendessDTO {


    private int outSideScheduleCode;

    private int scheduleCode;

    private String outsideAttendessName;

}
