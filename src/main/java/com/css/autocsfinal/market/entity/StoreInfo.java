package com.css.autocsfinal.market.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STORE_INFO")
@SequenceGenerator(
        name = "STORE_SEQ_GENERATOR", // 엔티티에서 지정한 시퀀스이름
        sequenceName = "SEQ_STORE_INFO_NO", // 실제 데이터베이스에 있는 시퀀스 명
        initialValue = 1, allocationSize = 1
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StoreInfo {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STORE_SEQ_GENERATOR"
    )
    @Column(name = "STORE_NO")
    private int storeNo;

    @Column(name = "LICENSE")
    private String license;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "REF_MEMBER_NO")
    private int refMemberNo;
}
