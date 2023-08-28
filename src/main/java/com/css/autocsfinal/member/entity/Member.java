package com.css.autocsfinal.member.entity;

import com.css.autocsfinal.main.entity.Todo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private int no;

    @Column(name = "ID")
    private String id;

    @Column(name = "PWD")
    private String pwd;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ROLE")
    private String role;


    // 투두리스트 1:N연관관계 매핑 N쪽이 FK를 가진 연관관계의 주인이므로 mappedBy로 연관관계 지정
    //cascade = CascadeType.ALL은 JPA에서 연관된 엔티티 간의 작업을 전파(propagate)시키는 옵션입니다.
    // 이 옵션을 설정하면 부모 엔티티에 수행한 변경 작업이 자식 엔티티에도 자동으로 적용
    @OneToMany(mappedBy = "member" ,cascade = CascadeType.ALL)
    private List<Todo> todoList = new ArrayList<>();

}

