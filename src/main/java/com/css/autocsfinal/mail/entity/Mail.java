package com.css.autocsfinal.mail.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TBL_MAIL")
@SequenceGenerator(
        name = "MAIL_NO",
        sequenceName = "SEQ_MAIL_NO",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    @Id
    @Column(name ="MAIL_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MAIL_NO"
    )
    private int mailNo;

    @Column(name = "RECEIVER")
    private String receiver;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTEXT")
    private String context;

    @Column(name = "GO_DATE")
    private Date goDate;

    @Column(name = "STATUS")
    private String status;

//    @OneToMany(mappedBy = "mail")
//    @Column(name ="MAIL_NO")
//    private List<MailList> mailList;
}
