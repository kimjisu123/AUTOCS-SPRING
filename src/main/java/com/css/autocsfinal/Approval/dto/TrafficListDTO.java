package com.css.autocsfinal.Approval.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TrafficListDTO {

    private String documentTitle;
    private List<String> trafficDate;
    private List<String> trafficTime;
    private List<String> from;
    private List<String> to;
    private List<Integer> distance;
    private List<String> business;
    private List<Integer> trafficPrice;
    private List<String> vehicle;
    private int empNo;
    private String empName;
    private List<Integer> allow;
    private List<Integer> receive;
}
