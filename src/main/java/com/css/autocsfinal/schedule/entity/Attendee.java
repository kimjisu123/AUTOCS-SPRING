package com.css.autocsfinal.schedule.entity;

import com.css.autocsfinal.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TBL_ATTENDEE")
@SequenceGenerator(
        name = "ATTENDEES_NO",
        sequenceName = "SEQ_ATTENDEE_NO",
        initialValue = 1, allocationSize = 1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Attendee {

    @Id
    @Column(name="ATTENDEE_CODE")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ATTENDEES_NO"
    )
    private int attendeeCode;

//    @ManyToOne
//    @JoinColumn(name="EMPLOYEE_NO")
//    private Employee employee;

    @ManyToOne
    @JoinColumn(name="SCHEDULE_CODE")
    private Schedule schedule;

}
