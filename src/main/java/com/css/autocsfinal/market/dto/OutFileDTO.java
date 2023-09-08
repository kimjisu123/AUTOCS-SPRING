package com.css.autocsfinal.market.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OutFileDTO {

    private int outFileNo;

    private String original;

    private String change;

    private LocalDate registDate;

    private String kind;

    private char state;

    private StoreInfoDTO store;
}
