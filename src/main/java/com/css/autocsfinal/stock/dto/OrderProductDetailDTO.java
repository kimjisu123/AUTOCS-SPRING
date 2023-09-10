package com.css.autocsfinal.stock.dto;

import com.css.autocsfinal.stock.entity.Order;
import com.css.autocsfinal.stock.entity.OrderDetail;
import com.css.autocsfinal.stock.entity.Product;
import com.css.autocsfinal.stock.entity.ProductDetail;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderProductDetailDTO {
    private int orderProductNo;
    private int quantity;
    private String status;
    private Date registDate;

    private OrderDetail refOrderNo;
    private ProductDetail refProductNo;
    private String etc;

}
