package com.css.autocsfinal.Approval.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TBL_BUSINESS_REPORT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SequenceGenerator(
        name = "SEQ_BUSINESS_GENERATOR",
        sequenceName = "SEQ_BUSINESS_REPORT_NO",
        initialValue = 1, allocationSize = 1
)
public class BusinessEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BUSINESS_GENERATOR")
    private int businessReport;

    @Column(name = "BUSINESS_TITLE")
    private String businessTitle;

    @Column(name = "BUSINESS_CONTENT")
    private String businessContent;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "DOCUMENT_CODE")
    private int documentCode;
}
