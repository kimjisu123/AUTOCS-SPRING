package com.css.autocsfinal.stock.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDetailDTO {
    private int productNo;
    private String name;
    private int stock;
    private int price;
    private String etc;
    private Date registDate;
    private Date unusedDate;
    private String status;
    private CategoryDTO category;
    private StandardDTO standard;
    private UnitDTO unit;
}
