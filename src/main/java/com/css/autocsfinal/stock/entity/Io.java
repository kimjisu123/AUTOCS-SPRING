package com.css.autocsfinal.stock.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TBL_PRODUCT_IO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Io {
    @Id
    @GeneratedValue(generator = "SEQ_PRODUCT_IO_NO")
    @SequenceGenerator(name = "SEQ_PRODUCT_IO_NO", sequenceName = "SEQ_PRODUCT_IO_NO", allocationSize = 1)
    @Column(name = "PRODUCT_IO_NO")
    private int productIoNo;
    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "IO")
    private String io;
    @Column(name = "REGIST_DATE")
    private Date registDate;
    @Column(name = "REF_PRODUCT_NO")
    private int refProductNo;
}
