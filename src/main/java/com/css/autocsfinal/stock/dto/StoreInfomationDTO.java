package com.css.autocsfinal.stock.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StoreInfomationDTO {
    private int storeNo;
    private String license;
    private int refMemberNo;
    private String address;
    private Integer phone;
    private String email;
    private String name;
    private String detailAddress;
}
