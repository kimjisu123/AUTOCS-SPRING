package com.css.autocsfinal.stock.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "TBL_BILL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Bill {

    @Id
    @Column(name = "BILL_NO")
    private int billNo;
    @Column(name = "REGIST_DATE")
    private Date registDate;
    @Column(name = "REF_COMPANY_NO")
    private int refCompanyNo;
    @Column(name = "REF_STORE_INFO_NO")
    private int refStoreInfoNo;

}
