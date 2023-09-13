package com.css.autocsfinal.stock.dto;

import com.css.autocsfinal.stock.entity.Category;
import com.css.autocsfinal.stock.entity.Standard;
import com.css.autocsfinal.stock.entity.Unit;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
