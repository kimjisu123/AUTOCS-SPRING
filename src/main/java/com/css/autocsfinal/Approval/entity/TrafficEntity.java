package com.css.autocsfinal.Approval.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.Name;
import javax.persistence.*;

@Entity
@Table(name = "TRAVELING_EXPENSES")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SequenceGenerator(
        name = "SEQ_TRAFFIC_GENERATOR",
        sequenceName = "SEQ_TRAVELING_EXPENSES_NO",
        allocationSize = 1, initialValue = 1
)
public class TrafficEntity {

    @Id
    @Column(name = "TRAVELING_EXPENSES_CODE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRAFFIC_GENERATOR")
    private int travelingExpensesCode;

    @Column(name = "TRAFFIC_DATE")
    private String trafficDate;

    @Column(name = "TIME")
    private String time;

    @Column(name = "START_POINT")
    private String startPoint;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "DISTANCE")
    private int distance;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "VEHICLE")
    private String vehicle;

    @Column(name = "BUSINESS")
    private String business;

    @Column(name = "DOCUMENT_CODE")
    private int documentCode;

    public TrafficEntity(int distance, String trafficDate, String time, String startPoint, String destination, int price, String vehicle, String business, int documentCode) {
        this.trafficDate = trafficDate;
        this.time = time;
        this.startPoint = startPoint;
        this.distance = distance;
        this.destination = destination;
        this.price = price;
        this.vehicle = vehicle;
        this.business = business;
        this.documentCode = documentCode;
    }
}
