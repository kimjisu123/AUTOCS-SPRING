package com.css.autocsfinal.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "템플릿 관련 VO")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TemplateVO {

    @Schema(description = "템플릿 아이디")
    private Integer templateId;

    @Schema(description = "템플릿 이름")
    private String templateName;

    @Schema(description = "템플릿 기타")
    private String templateEtc;
}
