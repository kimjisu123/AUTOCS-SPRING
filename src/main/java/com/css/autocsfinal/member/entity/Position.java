package com.css.autocsfinal.member.entity;

import lombok.*;

import javax.persistence.*;
import java.util.StringJoiner;


@Entity
@Table(name = "TBL_POSITION")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Position {

    @Id
    @Column(name = "POSITION_CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "UPPER_POSITION_CODE")
    private String upCode;
    @Override
    public String toString() {
        return new StringJoiner(", ", "{", "}")
                .add("\"code\" : \"" + code + "\"")
                .add("\"name\" : \"" + name + "\"")
                .add("\"upCode\" : \"" + upCode + "\"")
                .toString();
    }
}
