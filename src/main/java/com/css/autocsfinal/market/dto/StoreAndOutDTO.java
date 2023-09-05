package com.css.autocsfinal.market.dto;

import com.css.autocsfinal.member.dto.MemberDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StoreAndOutDTO {

    private int storeNo;

    private String license;

    private String address;

    private String detailAddress;

    private String email;

    private String name;

    private String phone;

    private int refMemberNo;

    private int refStoreNo;

    //나중에 에러 날 수 있음
    private MemberDTO member;

    //여기서부터 파일DTO
    private int outFileNo;

    private String original;

    private String change;

    private LocalDate registDate;

    private String kind;

    private char state;

    private StoreInfoDTO store;

    private String fileUrl;

}
