package com.css.autocsfinal.stock.entity;

import lombok.*;

import javax.persistence.*;
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
    @GeneratedValue(generator = "SEQ_BILL_NO") // 시퀀스 이름 지정
    @SequenceGenerator(name = "SEQ_BILL_NO", sequenceName = "SEQ_BILL_NO", allocationSize = 1)
    @Column(name = "BILL_NO")
    private int billNo;
    @Column(name = "REGIST_DATE")
    private Date registDate;
    @Column(name = "REF_COMPANY_NO")
    private int refCompanyNo;
    @Column(name = "REF_STORE_INFO_NO")
    private int refStoreInfoNo;
    @Column(name = "REF_ORDER_NO")
    private int refOrderNo;

}
