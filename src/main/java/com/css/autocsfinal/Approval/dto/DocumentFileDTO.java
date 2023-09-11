package com.css.autocsfinal.Approval.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DocumentFileDTO {

    private int documentFileCode;
    private String originName;
    private String modifyName;
    private int documentCode;
    private String filePath;
}
