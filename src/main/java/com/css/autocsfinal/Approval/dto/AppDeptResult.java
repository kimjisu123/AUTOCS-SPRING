package com.css.autocsfinal.Approval.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppDeptResult {

    private int id;
    private int parent;
    private String text;
}
