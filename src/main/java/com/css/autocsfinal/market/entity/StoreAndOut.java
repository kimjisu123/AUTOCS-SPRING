package com.css.autocsfinal.market.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "TBL_OUT_FILE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StoreAndOut {

    @Id
    @Column(name = "OUT_FILE_NO")
    private int fileNo;

    @Column(name = "ORIGINAL")
    private String orignal;

    @Column(name = "CHANGE")
    private String change;

    @Column(name = "REGIST_DATE")
    private LocalDate registDate;

    @Column(name = "KIND")
    private String kine;

    @Column(name = "STATE")
    private char state;

//    @Column(name = "REF_STORE_NO")
//    private Integer storeNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_STORE_NO")
    private StoreInfo store;
}
