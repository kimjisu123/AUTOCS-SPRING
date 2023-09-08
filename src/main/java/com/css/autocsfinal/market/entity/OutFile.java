package com.css.autocsfinal.market.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_OUT_FILE")
@SequenceGenerator(
        name = "OUT_FILE_SEQ_GENERATOR", // 엔티티에서 지정한 시퀀스이름
        sequenceName = "SEQ_OUT_FILE_NO", // 실제 데이터베이스에 있는 시퀀스 명
        initialValue = 1, allocationSize = 1
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OutFile {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "OUT_FILE_SEQ_GENERATOR"
    )
    @Column(name = "OUT_FILE_NO")
    private int fileNo;

    @Column(name = "ORIGINAL")
    private String orignal;

    @Column(name = "CHANGE")
    private String change;

    @Column(name = "REGIST_DATE")
    private Date registDate;

    @Column(name = "KIND")
    private String kine;

    @Column(name = "STATE")
    private char state;

    @Column(name = "REF_STORE_NO")
    private Integer storeNo;
}
