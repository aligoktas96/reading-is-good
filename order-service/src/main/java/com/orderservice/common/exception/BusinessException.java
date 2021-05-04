package com.orderservice.common.exception;

public class BusinessException extends RuntimeException
{
    private final Integer code;

    public BusinessException(ExceptionCode exception)
    {
        super(exception.getMessage());
        this.code = exception.getCode();
    }

    public Integer getCode()
    {
        return code;
    }

}
