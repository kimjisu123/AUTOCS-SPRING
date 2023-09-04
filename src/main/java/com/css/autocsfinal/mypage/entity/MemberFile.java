package com.css.autocsfinal.mypage.entity;

import com.css.autocsfinal.chart.entity.EmployeeAndPositionEntity;
import com.css.autocsfinal.market.entity.StoreInfo;
import com.css.autocsfinal.member.entity.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "TBL_MEMBER_FILE")
@SequenceGenerator(
        name = "SEQ_MEMBER_FILE_GENERATOR", // 엔티티에서 지정한 시퀀스 이름
        sequenceName = "SEQ_MEMBER_FILE_NO", // 실제 데이터베이스에 있는 시퀀스 명
        initialValue = 1, allocationSize = 1
)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MemberFile implements Serializable {

    @Id
    @Column(name = "M_FILE_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_MEMBER_FILE_GENERATOR"
    )
    private int fileNo;
    @Column(name = "ORIGIN_NAME")
    private String originName;
    @Column(name = "CHANGE_NAME")
    private String changeName;
    @Column(name = "TODAY")
    private LocalDate regDate = LocalDate.now();
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_MEMBER_NO")
    private Member member;

//    @Column(name = "REF_MEMBER_NO")
//    private int memberNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_EMPLOYEE_NO")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_STORE_NO")
    private StoreInfo storeInfo;


//


}
