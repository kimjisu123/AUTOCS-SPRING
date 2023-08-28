package com.css.autocsfinal.market.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApplyFormDTO {

    private int applyNo;

    private String address;

    private String name;

    private String email;

    private String license;

    private String state;

    private ApplyFileDTO file;
}
