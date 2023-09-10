package com.css.autocsfinal.Approval.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_POSITION")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppPositionEntity {

    @Id
    @Column(name = "POSITION_CODE")
    private String positionCode;

    @Column(name = "NAME")
    private String name;

}
