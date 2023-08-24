package com.css.autocsfinal.stock.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PRODUCT_UNIT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Unit {
    @Id
    @GeneratedValue(generator = "SEQ_PRODUCT_UNIT_NO")
    @SequenceGenerator(name = "SEQ_PRODUCT_UNIT_NO", sequenceName = "SEQ_PRODUCT_UNIT_NO", allocationSize = 1)
    @Column(name = "PRODUCT_UNIT_NO")
    private int productUnitNo;
    @Column(name = "NAME")
    private String name;
    @Column(name = "USEYN")
    private String useYn;
}
