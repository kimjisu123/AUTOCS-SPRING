package com.css.autocsfinal.schedule.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="TBL_SCHEDULE")
@SequenceGenerator(
        name = "SCHEDULE_NO",
        sequenceName = "SEQ_SCHEDULE_NO",
        initialValue = 1, allocationSize = 1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEntity {

    @Id
    @Column(name = "SCHEDULE_CODE")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SCHEDULE_NO"
    )
    private int scheduleCode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PLACE")
    private String place;

    @Column(name ="CONTENT")
    private String content;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @OneToMany(mappedBy = "scheduleCode")
    private List<AttendeeEntity> attendeeEntityList;

}
