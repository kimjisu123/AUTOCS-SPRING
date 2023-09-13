package com.css.autocsfinal.mail.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MailSaveDTO {

    private int mailNo;
    private String title;
    private String receiver;
    private String context;
    private Date goDate;
    private String status;
    private String position;

}
