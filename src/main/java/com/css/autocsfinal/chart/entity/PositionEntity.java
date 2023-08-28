package com.css.autocsfinal.chart.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.Name;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_POSITION")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PositionEntity {

    @Id
    @Column(name = "POSITION_CODE")
    private String positionCode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "UPPER_POSITION_CODE")
    private String upperPositionCode;

}
