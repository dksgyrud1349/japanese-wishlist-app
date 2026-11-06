package com.project.japanwishlist.common.response;

import com.project.japanwishlist.common.exception.ErrorCode;

public class ApiResponse<T> {

    private static final String SUCCESS_CODE = "SUCCESS";

    private final String code;
    private final String message;
    private final T data;

    private ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(SUCCESS_CODE, message, data);
    }

    public static <T> ApiResponse<T> success(T data) {
        return success("요청이 성공했습니다.", data);
    }

    public static <T> ApiResponse<T> failure(ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
