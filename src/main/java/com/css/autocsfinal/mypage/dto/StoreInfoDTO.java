package com.css.autocsfinal.mypage.dto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoreInfoDTO {

    private int storeNo;
    private String license;
    private int memberNo;
    private String address;
    private String storeName;
    private int phone;
    private String email;

}
