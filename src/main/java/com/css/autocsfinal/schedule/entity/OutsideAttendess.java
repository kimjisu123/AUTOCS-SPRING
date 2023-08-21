package com.css.autocsfinal.schedule.entity;

import lombok.*;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name="TBL_OUTSIDE_ATTENDEES")
@SequenceGenerator(
        name = "OUTSIDE_ATTENDEES_NO",
        sequenceName = "SEQ_OUTSIDE_ATTENDEES_NO",
        initialValue = 1, allocationSize = 1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OutsideAttendess {

    @Id
    @Column(name="OUTSIDE_ATTENDEES_CODE")
    @GeneratedValue(
            strategy =GenerationType.SEQUENCE,
            generator = "OUTSIDE_ATTENDEES_NO"
    )
    private int outSideScheduleCode;

    @ManyToOne
    @JoinColumn(name="SCHEDULE_CODE")
    private Schedule schedule;

    @Column(name="OUTSIDE_ATTENDEES_NAME")
    private String outsideAttendessName;

}
