package com.css.autocsfinal.stock.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PRODUCT_STANDARD")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Standard {
    @Id
    @GeneratedValue(generator = "SEQ_PRODUCT_STANDARD_NO")
    @SequenceGenerator(name = "SEQ_PRODUCT_STANDARD_NO", sequenceName = "SEQ_PRODUCT_STANDARD_NO", allocationSize = 1)
    @Column(name = "PRODUCT_STANDARD_NO")
    private int productStandardNo;
    @Column(name = "NAME")
    private String name;
    @Column(name = "USEYN")
    private String useYn;
}
