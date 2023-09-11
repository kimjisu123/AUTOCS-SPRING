package com.css.autocsfinal.stock.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CompanyDTO {
    private int companyNo;
    private String name;
    private String licenseNo;
    private String address;
    private String boss;
    private String businessCondition;

}
