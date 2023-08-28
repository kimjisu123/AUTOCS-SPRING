package com.css.autocsfinal.market.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApplyFileDTO {

    private int fileNo;

    private String original;

    private String change;

    private Date registDate;

    private String kind;
}
