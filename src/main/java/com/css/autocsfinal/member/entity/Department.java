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
    private String code;

    @Column(name = "NAME")
    private String name;

}
