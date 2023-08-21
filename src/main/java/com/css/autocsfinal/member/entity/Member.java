package com.css.autocsfinal.member.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MEMBER")
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR", // 엔티티에서 지정한 시퀀스이름
        sequenceName = "SEQ_MEMBER_NO", // 실제 데이터베이스에 있는 시퀀스 명
        initialValue = 1, allocationSize = 1
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {

    @Id
    @Column(name = "MEMBER_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR"
    )
    private int memberNo;

    @Column(name = "ID")
    private String Id;

    @Column(name = "PWD")
    private String Pwd;

    @Column(name = "STATE")
    private String State;

    @Column(name = "ROLE")
    private String Role;

    @Override
    public String toString() {
        return "Member [memberNo=" + memberNo + ", Id=" + Id + ", Pwd=" + Pwd
                + ", State=" + State + ", Role=" + Role + "]";
    }
}