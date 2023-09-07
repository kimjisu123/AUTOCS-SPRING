package com.css.autocsfinal.stock.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STORE_INFO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StoreInfomation {

    @Id
    @Column(name = "STORE_NO")
    private int storeNo;
    @Column(name = "LICENSE")
    private String license;
    @Column(name = "REF_MEMBER_NO")
    private int refMemberNo;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PHONE")
    private Integer phone;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DETAIL_ADDRESS")
    private String detailAddress;
}
