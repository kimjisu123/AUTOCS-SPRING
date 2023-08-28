package com.css.autocsfinal.member.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "TBL_POSITION")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Position {

    @Id
    @Column(name = "POSITION_CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "UPPER_POSITION_CODE")
    private String upCode;

}
