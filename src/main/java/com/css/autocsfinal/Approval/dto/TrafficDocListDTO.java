package com.css.autocsfinal.Approval.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrafficDocListDTO {

    private int travelingExpensesCode;
    private String trafficDate;
    private String time;
    private String StartPoint;
    private String destination;
    private int distance;
    private int price;
    private String vehicle;
    private String business;
    private int documentCode;
}
