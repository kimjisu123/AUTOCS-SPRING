package com.css.autocsfinal.schedule.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {


    private int scheduleCode;

    private String name;

    private String place;

    private String content;

    private Date startDate;

    private Date endDate;

}