package com.casa.samala.controller.response;

/**
 * ApiResponseStatusEnum
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: ApiResponseStatusEnum.java, v 0.1 2024-01-20  11.37 Ahmad Isyfalana Amin Exp $
 */
public enum ApiResponseStatusEnum {
    SUCCESS(true, 10, "Success"),

    FAILED(false, 30, "Failed"),

    ;

    private boolean success;

    private int errorCode;

    private String errorMessage;

    ApiResponseStatusEnum(boolean success, int errorCode, String errorMessage) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}