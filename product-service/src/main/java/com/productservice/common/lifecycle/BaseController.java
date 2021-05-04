package com.productservice.common.lifecycle;

import com.productservice.common.response.Response;
import org.springframework.http.ResponseEntity;


public abstract class BaseController {
    protected <RESPONSE> ResponseEntity<Response> ok(RESPONSE data) {
        return ResponseEntity.ok(wrap(data));
    }

    private <RESPONSE> Response wrap(RESPONSE body) {
        return Response.builder()
                .data(body)
                .build();
    }
}
