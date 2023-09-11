package com.css.autocsfinal.Approval.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class ReceiverDocumentEntityKey implements Serializable {

    @Column(name = "EMPLOYEE_NO")
    private int no;
    @Column(name = "DOCUMENT_CODE")
    private int code;
}
