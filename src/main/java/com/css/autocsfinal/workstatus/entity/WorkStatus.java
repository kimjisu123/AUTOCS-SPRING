package com.css.autocsfinal.workstatus.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="TBL_WORK_STATUS")
@SequenceGenerator(
        name = "WORK_STATUS_NO",
        sequenceName = "SEQ_WORK_STATUS_NO",
        initialValue = 1, allocationSize = 1
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WorkStatus {

    @Id
    @Column(name = "WORK_STATUS_CODE")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "WORK_STATUS_NO"
    )
    private int workStatusCode;

    @Column(name ="ATTENDANCE_TIME")
    private Date attendanceTime;

    @Column(name = "QUITTING_TIME")
    private Date quittingTime;

    @Column(name = "VACATION_STATUS")
    private char vacationStatus;

    @Column(name = "ABSENCE_WORK_STATUS")
    private char absenceWorkStatus;

    @Column(name = "WORKING_DATE")
    private String workingDate;

    @Column(name = "EXTENSION_TIME")
    private Date extensionTime;

}
