package com.css.autocsfinal.member.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "TBL_DEPARTMENT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Department {

    @Id
    @Column(name = "DEPARTMENT_CODE")
    private int code;

    @Column(name = "NAME")
    private String name;

}
