package com.css.autocsfinal.stock.dto;

import lombok.*;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StandardDTO {
    private int productStandardNo;
    private String name;
    private String useYn;
}
