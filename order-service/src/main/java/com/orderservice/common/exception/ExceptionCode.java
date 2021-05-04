package com.orderservice.common.exception;

public enum ExceptionCode
{
    ORDER_NOT_FOUND(1001, "Order ID could not found."),
    PRODUCT_AMOUNT_INSUFFICIENT(1002, "Product amount is not enough.");

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
