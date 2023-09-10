package com.css.autocsfinal.Approval.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PurchaseListDTO {

    private String documentTitle;
    private List<String> productName;
    private List<String> productSize;
    private List<Integer> amount;
    private List<Integer> price;
    private List<String> note;
    private List<Integer> allow;
    private List<Integer> receive;
    private int empNo;
    private String empName;
}
