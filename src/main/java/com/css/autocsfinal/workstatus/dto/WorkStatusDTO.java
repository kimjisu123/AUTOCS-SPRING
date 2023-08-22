package com.css.autocsfinal.workstatus.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WorkStatusDTO {


    private int workStatusCode;

    private Date attendanceTime;

    private Date quittingTime;

    private Date totalHoursDuty;

    private char vactionStatus;

    private char absenceWorkStatus;

    private Date workingDate;

    public WorkStatusDTO(HttpStatus httpStatus, String 조회_성공, Object o) {
    }

}
