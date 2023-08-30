package com.css.autocsfinal.market.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StoreInfoDTO {

    private int storeNo;

    private String license;

    private String address;

    private String email;

    private String name;

    private String phone;

    private int refMemberNo;
}
