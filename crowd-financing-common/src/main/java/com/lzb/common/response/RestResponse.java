package com.lzb.common.response;

import lombok.Data;

@Data
public class RestResponse<T> {
    private int state;
    private String message;
    private T data;
    private String token;

    public RestResponse(int state,String message, T data,String token) {
       this.state=state;
       this.message=message;
        this.data=data;
        this.token=token;

    }
}
