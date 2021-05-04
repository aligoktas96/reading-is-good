package com.productservice.common.exception;

public enum ExceptionCode
{
    PRODUCT_NOT_FOUND(1000, "Product ID could not found.");

    private final Integer code;
    private final String message;

    ExceptionCode(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }
}
