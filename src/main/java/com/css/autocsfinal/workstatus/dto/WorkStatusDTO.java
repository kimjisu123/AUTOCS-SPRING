package com.css.autocsfinal.workstatus.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkStatusDTO {

    private int workStatusCode;

    private Date attendanceTime;

    private Date quittingTime;

    private char vacationStatus;

    private char absenceWorkStatus;

    private Date extensionTime;
}
