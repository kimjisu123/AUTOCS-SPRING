package com.css.autocsfinal.Approval.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "TBL_DOCUMENT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SequenceGenerator(
        name = "DOCUMENT_SEQ_GENERATOR",
        sequenceName = "SEQ_DOCUMENT_NO",
        initialValue = 1, allocationSize = 1
)
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCUMENT_SEQ_GENERATOR")
    @Column(name = "DOCUMENT_CODE")
    private int documentCode;

    @Column(name = "EMPLOYEE_NO")
    private int employeeNO;

    @Column(name = "APPLICATION_DATE")
    private Date applicationDate;

    @Column(name = "DOCUMENT_TYPE")
    private String documentType;

    @Column(name = "STATUS")
    private String status;

    public DocumentEntity(int employeeNO, Date applicationDate, String documentType) {
        this.employeeNO = employeeNO;
        this.applicationDate = applicationDate;
        this.documentType = documentType;
    }
}
