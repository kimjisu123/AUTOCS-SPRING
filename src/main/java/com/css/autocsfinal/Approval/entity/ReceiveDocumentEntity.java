package com.css.autocsfinal.Approval.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TBL_RECEIVER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReceiveDocumentEntity {

    @EmbeddedId
    private ReceiverDocumentEntityKey key;

    @ManyToOne
    @JoinColumn(name = "DOCUMENT_CODE", insertable = false, updatable = false)
    private DocumentEmployeeEntity document;

    @Column(name = "STATUS")
    private String status;
}
