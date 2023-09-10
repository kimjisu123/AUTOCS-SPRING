package com.css.autocsfinal.Approval.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_EXPENSES_CHARGE")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SequenceGenerator(
        name = "SEQ_PAY_GENERATOR",
        sequenceName = "SEQ_BILLING_NO",
        initialValue = 1, allocationSize = 1
)
public class PayEntity {

    @Id
    @Column(name = "BILLING_CODE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PAY_GENERATOR")
    private int billingCode;

    @Column(name = "TODAY")
    private Date day;

    @Column(name = "BUSINESS")
    private String business;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "DOCUMENT_CODE")
    private int documentCode;
}
