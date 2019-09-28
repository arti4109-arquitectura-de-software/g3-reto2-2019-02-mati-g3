package com.atpos.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import static com.atpos.api.commons.costant.Constants.SUCCESS_CODE;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusDTO {

    public String code;
    private String message;
    private String detailMessageError;

    public StatusDTO(String code, String message,String detailMessageError) {
        this.code = code;
        this.message = message;
        this.detailMessageError = detailMessageError;
    }

    public StatusDTO(String code, String message) {
        this.code = code;
        this.message = message;
        this.detailMessageError = null;
    }

    public StatusDTO(String message) {
        this.code = SUCCESS_CODE;
        this.message = message;
        this.detailMessageError = null;
    }
}
