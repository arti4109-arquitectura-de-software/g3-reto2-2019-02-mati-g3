package com.atpos.api.commons.costant;


import org.springframework.http.HttpStatus;

public class Constants {

    /*Commons*/

    public static final String SUCCESS_CODE = String.valueOf(HttpStatus.OK.value());
    public static final String SUCCESS_MESSAGE = "Successful process";
    public static final String SERVER_ERROR_CODE = String.valueOf( HttpStatus.INTERNAL_SERVER_ERROR.value());
    public static final String SERVER_ERROR_MESSAGE = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();

    public static final String BAD_PROCESS_CODE = "400";
    public static final String BAD_PROCESS_MESSAGE = "Process Error";

}
