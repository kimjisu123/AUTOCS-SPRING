package com.css.autocsfinal.workstatus.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WorkStatusListPk implements Serializable {

    private int employeeNo;

    private int workStatusCode;

}
