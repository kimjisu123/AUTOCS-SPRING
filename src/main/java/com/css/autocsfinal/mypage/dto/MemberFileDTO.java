package com.css.autocsfinal.mypage.dto;

import com.css.autocsfinal.member.entity.Member;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberFileDTO {

    private int fileNo;
    private String originName;
    private String changeName;
    private Date regDate;
    private int memberNo;

}
