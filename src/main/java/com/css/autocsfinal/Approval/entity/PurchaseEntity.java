package com.css.autocsfinal.Approval.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PURCHASE_REQUEST")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SequenceGenerator(
        name = "PURCHASE_SEQ_GENERATOR",
        sequenceName = "SEQ_PURCHASE_REQUEST_NO",
        initialValue = 1, allocationSize = 1
)
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PURCHASE_SEQ_GENERATOR")
    @Column(name = "PURCHASE_REQUEST_CODE")
    private int purchaseRequestCode;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "STANDARD")
    private String standard;

    @Column(name = "AMOUNT")
    private int amount;

    @Column(name = "UNIT_PRICE")
    private int unitPrice;

    @Column(name = "TOTAL_PRICE")
    private int totalPrice;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "DOCUMENT_CODE")
    private int documentCode;
}

