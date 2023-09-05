package com.css.autocsfinal.Approval.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_VACATION_APP")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SequenceGenerator(
        name = "SEQ_VACATION_GENERATOR",
        sequenceName = "SEQ_VACATION_APP_NO",
        allocationSize = 1, initialValue = 1
)
public class VacationEntity {

    @Id
    @Column(name = "VACATION_APP_CODE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VACATION_GENERATOR")
    private int vacationAppCode;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "VACATION_REASON")
    private String vacationReason;

    @Column(name = "VACATION_TYPE")
    private String vacationType;

    @Column(name = "HALF_DAY_OFF")
    private String halfDayOff;

    @Column(name = "DOCUMENT_CODE")
    private int documentCode;
}
