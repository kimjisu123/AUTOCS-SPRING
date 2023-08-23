package com.css.autocsfinal.stock.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PRODUCT_CATEGORY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Category {
    @Id
    @GeneratedValue(generator = "SEQ_PRODUCT_CATEGORY_NO") // 시퀀스 이름 지정
    @SequenceGenerator(name = "SEQ_PRODUCT_CATEGORY_NO", sequenceName = "SEQ_PRODUCT_CATEGORY_NO", allocationSize = 1) // 시퀀스 이름 및 속성 지정
    @Column(name = "PRODUCT_CATEGORY_NO")
    private int productCategoryNo;
    @Column(name = "NAME")
    private String name;
    @Column(name = "USEYN")
    private String useYn;
}
