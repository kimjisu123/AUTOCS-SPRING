package com.css.autocsfinal.Approval.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TBL_DOCUMENT_FILES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SequenceGenerator(
        name = "SEQ_DOCUMENT_FILE_GENERATOR",
        sequenceName = "SEQ_DOCUMENT_FILES_NO",
        allocationSize = 1, initialValue = 1
)
public class DocumentFileEntity {

    @Id
    @Column(name = "DOCUMENT_FILES_CODE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOCUMENT_FILE_GENERATOR")
    private int documentFileCode;

    @Column(name = "ORIGIN_NAME")
    private String originName;

    @Column(name = "MODIFY_NAME")
    private String modifyName;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "DOCUMENT_CODE")
    private int documentCode;

    public DocumentFileEntity(String originName, String modifyName, String filePath, int documentCode) {
        this.originName = originName;
        this.modifyName = modifyName;
        this.filePath = filePath;
        this.documentCode = documentCode;
    }
}
