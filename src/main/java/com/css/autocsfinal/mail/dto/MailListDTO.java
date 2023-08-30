package com.css.autocsfinal.mail.dto;

import com.css.autocsfinal.mail.entity.Mail;
import com.css.autocsfinal.member.entity.Employee;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MailListDTO {

    private int employeeNo;

    private int mailNo;

    private Employee employee;

    private Mail mail;
}
