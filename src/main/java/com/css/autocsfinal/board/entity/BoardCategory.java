package com.css.autocsfinal.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_BOARD_CATEGORY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardCategory {

    @Id
    @Column(name = "BOARD_CATEGORY_NO")
    private int categoryNo;

    @Column(name = "NAME")
    private String categoryName;


}
