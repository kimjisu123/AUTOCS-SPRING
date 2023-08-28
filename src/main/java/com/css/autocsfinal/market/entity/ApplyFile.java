package com.css.autocsfinal.market.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "TBL_APPLY_FILE")
@SequenceGenerator(
        name = "APPLY_FILE_SEQ_GENERATOR", // 엔티티에서 지정한 시퀀스이름
        sequenceName = "SEQ_APPLY_FILE_NO", // 실제 데이터베이스에 있는 시퀀스 명
        initialValue = 1, allocationSize = 1
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApplyFile {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "APPLY_FILE_SEQ_GENERATOR"
    )
    @Column(name = "APPLY_FILE_NO")
    private int fileNo;

    @Column(name = "ORIGINAL")
    private String orignal;

    @Column(name = "CHANGE")
    private String change;

    @Column(name = "REGIST_DATE")
    private Date registDate;

    @Column(name = "KIND")
    private String kine;
}
