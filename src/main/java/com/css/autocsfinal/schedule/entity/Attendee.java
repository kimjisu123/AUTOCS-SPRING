package com.css.autocsfinal.schedule.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_ATTENDEE")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeEntity {

    @Id
    @Column(name="ATTENDEE_CODE")
    private int attendeeCode;

    @ManyToOne
    @JoinColumn(name = "SCHEDULE_CODE")
    private ScheduleEntity ScheduleEntity;

    @Column(name="EMPLOYEE_NO")
    private int emplotyeeNo;

}
