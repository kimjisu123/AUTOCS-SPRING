package com.css.autocsfinal.market.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApplyFileDTO {

    private int fileNo;

    private String original;

    private String change;

    private LocalDate registDate;

    private String kind;

    }
