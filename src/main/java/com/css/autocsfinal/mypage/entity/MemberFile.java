package com.css.autocsfinal.mypage.entity;

import com.css.autocsfinal.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TBL_MEMBER_FILE")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberFile {

    @Id
    @Column(name = "M_FILE_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STORE_SEQ_CENERATOR"
    )
    private int fileNo;
    @Column(name = "ORIGIN_NAME")
    private String originName;
    @Column(name = "CHANGE_NAME")
    private String changeName;
    @Column(name = "TODAY")
    private Date regDate;

    @ManyToOne
    @JoinColumn(name="MEMBER_NO")
    private Member member;
}
