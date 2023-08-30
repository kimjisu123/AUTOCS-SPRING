package com.css.autocsfinal.market.dto;

import com.css.autocsfinal.member.dto.MemberDTO;
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

    //나중에 에러 날 수 있음
    private MemberDTO member;
}
