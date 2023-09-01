package com.css.autocsfinal.Approval.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TBL_RECEIVER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(ReceiverEntityKey.class)
public class ReceiverEntity {

    @Id
    @Column(name = "EMPLOYEE_NO")
    private int employeeNo;

    @Id
    @Column(name = "DOCUMENT_CODE")
    private int documentCode;
}
