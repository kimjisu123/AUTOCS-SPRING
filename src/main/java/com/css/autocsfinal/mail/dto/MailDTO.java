package com.css.autocsfinal.mail.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {

    private int mailNo;

    private String send;

    private String title;

    private String context;
    private Date goDate;
    private char status;

}
