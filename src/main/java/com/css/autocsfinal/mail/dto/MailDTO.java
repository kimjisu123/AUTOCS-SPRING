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
    private String receiver;
    private String title;
    private String context;
    private Date goDate;
    private String status;
    private String position;

}
