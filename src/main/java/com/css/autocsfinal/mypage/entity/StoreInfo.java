//package com.css.autocsfinal.mypage.entity;
//
//
//import com.css.autocsfinal.member.entity.Member;
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "TBL_STORE_INFO")
//@SequenceGenerator(
//        name = "STORE_SEQ_GENERATOR", // 엔티티에서 지정한 시퀀스 이름
//        sequenceName = "SEQ_STORE_NO", // 실제 데이터베이스에 있는 시퀀스 명
//        initialValue = 1, allocationSize = 1
//)
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
//public class StoreInfo {
//
//    @Id
//    @Column(name = "STORE_NO")
//    private int storeNo;
//    @Column(name="LICENSE")
//    private String license;
//    @Column(name = "ADDRESS")
//    private String address;
//    @Column(name = "NAME")
//    private String storeName;
//    @Column(name="PHONE")
//    private int phone;
//    @Column(name = "EMAIL")
//    private String email;
//
//    @OneToOne
//    @JoinColumn(name ="MEMBER_NO")
//    private Member memberNo;
//}
