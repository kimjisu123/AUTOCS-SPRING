package com.css.autocsfinal.market.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_APPLY_FORM")
@SequenceGenerator(
        name = "APPLY_SEQ_GENERATOR", // 엔티티에서 지정한 시퀀스이름
        sequenceName = "SEQ_APPLY_FORM_NO", // 실제 데이터베이스에 있는 시퀀스 명
        initialValue = 1, allocationSize = 1
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApplyFormNApplyFile {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "APPLY_SEQ_GENERATOR"
    )
    @Column(name = "APPLY_FORM_NO")
    private int applyNo;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "DETAIL_ADDRESS")
    private String detailAddress;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "LICENSE")
    private String license;

    @Column(name = "STATE")
    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_APPLY_FILE_NO")
    private ApplyFile file;


}
