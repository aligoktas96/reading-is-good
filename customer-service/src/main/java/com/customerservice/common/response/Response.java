package com.customerservice.common.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response implements BaseRestResponse
{
    private Object data;
}
