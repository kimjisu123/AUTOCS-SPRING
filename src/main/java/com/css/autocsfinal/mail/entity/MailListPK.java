package com.css.autocsfinal.mail.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MailListPK implements Serializable {

    private int employeeNo;

    private int mailNo;
}
