package com.css.autocsfinal.Approval.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseDTO extends DocumentDTO{

    private int purchaseRequestCode;
    private String productName;
    private String standard;
    private int amount;
    private int unitPrice;
    private int totalPrice;
    private String remarks;
    private String purchaseTitle;
}
