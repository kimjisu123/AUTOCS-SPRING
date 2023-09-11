package com.css.autocsfinal.stock.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_COMPANY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Company {
    @Id
    @Column(name = "COMPANY_NO")
    private int companyNo;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LICENSE_NO")
    private String licenseNo;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "BOSS")
    private String boss;
    @Column(name = "BUSINESS_CONDITION")
    private String businessCondition;
}
