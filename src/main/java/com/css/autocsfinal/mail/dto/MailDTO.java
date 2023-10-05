package com.css.autocsfinal.mail.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {

    private int mailNo;
    private List<String> receiver;
    private String title;
    private String context;
    private Date goDate;
    private String status;
    private List<String> position;

}
